console.log("STARTING...");

const express = require("express");
const fs = require("fs");
const path = require("path");
const mic = require("mic");

const app = express();

// Test Route
app.get("/", (req, res) => {
  res.send("Server is working");
});

// File Stream (for demo)
app.get("/audio", (req, res) => {
  const filePath = path.join(__dirname, "sample.mp3");

  if (!fs.existsSync(filePath)) {
    return res.status(404).send("No audio file found");
  }

  console.log("📁 Serving audio file");

  res.sendFile(filePath);
});


// Live Mic Stream
let micInstance = null;
let micStream = null;

const MAX_BUFFER = 50;
let audioBuffer = [];
let clients = [];

// Start mic ONCE (not per request)
micInstance = mic({
  rate: "16000",
  channels: "1",
  debug: false,
  exitOnSilence: 0,
  fileType: "wav",
});

micStream = micInstance.getAudioStream();

micStream.on("data", (chunk) => {
  // store recent chunks
  audioBuffer.push(chunk);
  if (audioBuffer.length > MAX_BUFFER) {
    audioBuffer.shift();
  }

  // send to all clients
  clients.forEach((res) => {
    res.write(chunk);
  });
});

micStream.on("error", (err) => {
  console.log("Mic error:", err);
});

micInstance.start();
console.log("🎤 Mic started (persistent)");

// Route
app.get("/audio-live", (req, res) => {
  console.log("🎧 Client connected:", req.ip);

  res.writeHead(200, {
    "Content-Type": "audio/wav",
    "Transfer-Encoding": "chunked",
  });

  // send buffered audio first
  audioBuffer.forEach((chunk) => {
    res.write(chunk);
  });

  clients.push(res);

  req.on("close", () => {
    console.log("🔌 Client disconnected");

    clients = clients.filter((client) => client !== res);
  });
});

// Start Server
app.listen(3000, "0.0.0.0", () => {
  console.log("Server running on port 3000");
});