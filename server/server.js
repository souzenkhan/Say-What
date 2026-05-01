// const express = require("express");
// const mic = require("mic");
// const cors = require("cors");

// const app = express();
// const PORT = 3000;

// app.use(cors());

// // Create mic instance
// const micInstance = mic({
//   rate: "44100",
//   channels: "1",
//   debug: false,
//   exitOnSilence: 0,
// });

// const micStream = micInstance.getAudioStream();

// // Endpoint: stream audio
// app.get("/audio", (req, res) => {
//   res.writeHead(200, {
//     "Content-Type": "audio/wav",
//     "Transfer-Encoding": "chunked",
//   });

//   micStream.pipe(res);
// });

// // Start server
// app.listen(PORT, "0.0.0.0", () => {
//   console.log(`Server running on http://YOUR_IP:${PORT}/audio`);
// });

// // Start mic
// micInstance.start();

console.log("STARTING...");

const express = require("express");
const fs = require("fs");
const path = require("path");

const app = express();

// ✅ Keep this (test route)
app.get("/", (req, res) => {
  res.send("Server is working");
});

// ✅ 🔥 PUT YOUR /audio ROUTE RIGHT HERE
app.get("/audio", (req, res) => {
  const filePath = path.join(__dirname, "sample.mp3");

  if (!fs.existsSync(filePath)) {
    return res.status(404).send("No audio file found");
  }

  res.sendFile(filePath);
});

// ✅ Server start (always at the bottom)
app.listen(3000, "0.0.0.0", () => {
  console.log("✅ Server running on port 3000");
});