console.log("STARTING...");

const express = require("express");
const fs = require("fs");
const path = require("path");
const { spawn } = require("child_process");

const app = express();

// =========================
// TEST ROUTE
// =========================
app.get("/", (req, res) => {
  res.send("Server is working");
});

// =========================
// FILE STREAM (stable fallback)
// =========================
app.get("/audio", (req, res) => {
  const filePath = path.join(__dirname, "sample.mp3");

  if (!fs.existsSync(filePath)) {
    return res.status(404).send("No audio file found");
  }

  console.log("📁 Serving audio file to:", req.ip);

  res.sendFile(filePath);
});

// =========================
// LIVE MP3 STREAM
// =========================

let clients = [];

// Live stream route
app.get("/audio-live", (req, res) => {
  console.log("🎧 Client connected:", req.ip);

  res.writeHead(200, {
    "Content-Type": "audio/mpeg",
    "Transfer-Encoding": "chunked",
    "Cache-Control": "no-cache",
    Connection: "keep-alive",
  });

  clients.push(res);

  console.log("📱 Active clients:", clients.length);

  req.on("close", () => {
    console.log("🔌 Client disconnected");

    clients = clients.filter((client) => client !== res);

    console.log("📱 Active clients:", clients.length);
  });

  res.on("error", () => {
    clients = clients.filter((client) => client !== res);

    console.log("⚠️ Removed broken client");
  });
});

// =========================
// FFmpeg microphone capture
// =========================

const ffmpeg = spawn("ffmpeg", [
  "-f", "avfoundation",
  "-i", ":1",
  "-ac", "1",
  "-ar", "16000",
  "-b:a", "64k",
  "-f", "mp3",
  "-"
]);

ffmpeg.stdout.on("data", (chunk) => {
  clients.forEach((client) => {
    try {
      client.write(chunk);
    } catch (err) {
      console.log("Client write error");
    }
  });
});

ffmpeg.stderr.on("data", (data) => {
  // Optional debug:
  // console.log(data.toString());
});

ffmpeg.on("close", () => {
  console.log("FFmpeg process closed");
});

console.log("🎤 FFmpeg live MP3 stream started");

// =========================
// START SERVER
// =========================
app.listen(3000, "0.0.0.0", () => {
  console.log("✅ Server running on port 3000");
});