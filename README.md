# 📡 Audio Streaming Setup (Step-by-Step)

## Please read before running the project

This project uses a **local IP address**.

- Every person running the server will have a different IP
- You MUST replace the IP in the app code

---

# STEP 1 — Install Dependencies

## Install Node packages

Server:

```bash
cd server
npm install
```

App:

```bash
cd app
npm install
```

---

# STEP 2 — Install FFmpeg (REQUIRED FOR LIVE AUDIO)

Mac:

```bash
brew install ffmpeg
```

Verify installation:

```bash
ffmpeg -version
```

---

# STEP 3 — Find YOUR IP Address

On your laptop:

```bash
ifconfig
```

Look for:

```text
inet 192.168.x.x
```

Example:

```text
192.168.12.163
```

This is YOUR IP address.

---

# STEP 4 — Update the App (REQUIRED)

Go to:

```text
/app/screens/AudioControlScreen.tsx
```

Find:

```ts
uri: "http://192.168.12.163:3000/audio";
```

Replace with YOUR IP:

```ts
uri: "http://192.168.x.x:3000/audio";
```

For live audio:

```ts
uri: "http://192.168.x.x:3000/audio-live";
```

If the IP is incorrect, audio will not work.

Please keep a log of local IPs:

```text
Souzen: http://192.168.12.163:3000
Fatima:
Maryam:
Ngozi:
Avni:
```

---

# STEP 5 — Configure Microphone Device (LIVE AUDIO)

Run:

```bash
ffmpeg -f avfoundation -list_devices true -i ""
```

Look under:

```text
AVFoundation audio devices:
```

Example:

```text
[1] MacBook Pro Microphone
```

In `server.js`, update:

```js
"-i", ":1",
```

Replace `1` with your microphone index if different.

---

# STEP 6 — Start the Server

```bash
cd server
node server.js
```

Expected output:

```text
🎤 FFmpeg live MP3 stream started
✅ Server running on port 3000
```

---

# STEP 7 — Run the App

```bash
cd app
npx expo start
```

Use LAN mode:

- press `shift + l`
- OR select LAN manually

Do NOT use Tunnel mode.

---

# STEP 8 — Same WiFi Network

Laptop and phone must both be on:

- same WiFi
- not mobile data
- not guest WiFi
- not VPN

---

# STEP 9 — Test in Browser BEFORE App

## Stable file playback

Open on phone:

```text
http://YOUR_IP:3000/audio
```

## Live microphone stream

Open on phone:

```text
http://YOUR_IP:3000/audio-live
```

Example:

```text
http://192.168.12.163:3000/audio-live
```

---

# STEP 10 — Use the App

1. Open app on phone
2. Navigate to Audio Control screen
3. Press Play
4. Audio should begin streaming

---

# TROUBLESHOOTING

## No audio

- Check IP address
- Verify server is running
- Ensure same WiFi

## Live audio has no sound

- Check microphone permissions:
  - System Settings → Privacy & Security → Microphone

- Verify correct FFmpeg mic index in `server.js`

## iPhone buffering or overlapping audio

- Close all previous `/audio-live` tabs
- Reopen only one stream
- Restart Safari if needed

## Works on laptop but not phone

- Wrong IP
- Expo running in Tunnel mode
- Firewall blocking port 3000

---

# CURRENT SYSTEM

```text
/audio       → stable MP3 file playback
/audio-live  → live microphone MP3 stream
```

---

# CURRENT LIMITATIONS

- Live stream may still have slight delay
- Safari/iPhone buffering behavior can vary
- WebRTC would be needed for true low-latency production streaming

---

# FUTURE WORK

- WebRTC real-time streaming
- Bluetooth hearing aid routing
- Lower latency audio pipeline
- Audio compression optimization

---

# OWNER

Souzen — Networking & Streaming
