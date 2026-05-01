# Live Audio Streaming (Mic) — Quick Test

## Start the server

```bash
cd server
node server.js
```

## Record live audio

Open a new terminal and run:

```bash
curl http://localhost:3000/audio-live --output test.wav
```

Speak into your mic for a few seconds, then press Ctrl + C.

## Play the recording

```bash
afplay test.wav
```

## Expected result

You should hear your voice or background noise from the mic.

## Notes

- This confirms live mic streaming is working
- `/audio-live` may not play directly in browsers or mobile apps
- Use `/audio` for stable playback in the app

## Troubleshooting

- No sound: check microphone permissions (System Settings → Privacy → Microphone)
- File won’t play: ensure `fileType: "wav"` is set in the server
- Very small file: mic stream is not running properly
