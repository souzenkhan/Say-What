console.log("STARTING...");

const express = require("express");
const fs = require("fs");
const path = require("path");
const { spawn } = require("child_process");

const app = express();

app.get("/", (req, res) => {
  res.send("Server is working");
});

app.get("/audio", (req, res) => {
  const filePath = path.join(__dirname, "sample.mp3");

  if (!fs.existsSync(filePath)) {
    return res.status(404).send("No audio file found");
  }

  console.log("📁 Serving audio file to:", req.ip);

  res.sendFile(filePath);
});

let clients = [];

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

function startFFmpegLiveStream() {
  const ffmpeg = spawn("ffmpeg", [
    "-re",
    "-stream_loop",
    "-1",
    "-i",
    path.join(__dirname, "sample.mp3"),
    "-f",
    "mp3",
    "-",
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

  ffmpeg.stderr.on("data", () => {});

  ffmpeg.on("error", (err) => {
    console.log("⚠️ FFmpeg could not start:", err.message);
    console.log("Server will still run with the /audio fallback endpoint.");
  });

  ffmpeg.on("close", () => {
    console.log("FFmpeg process closed");
  });

  console.log("🎤 FFmpeg live MP3 stream attempted");
}

startFFmpegLiveStream();

app.listen(3000, "0.0.0.0", () => {
  console.log("✅ Server running on port 3000");
});