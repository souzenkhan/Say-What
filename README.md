# 📡 Audio Streaming Setup (Step-by-Step)

## Please read before running the project

This project uses a **local IP address**.

- Every person running the server will have a **different IP**
- You MUST replace it in the app code

---

# STEP 1 — Find YOUR IP Address

On your laptop (the one running the server):

```bash
ifconfig
```

Look for something like:

```text
inet 192.168.x.x
```

### Example:

```text
192.168.12.163
```

This is YOUR IP

---

# STEP 2 — Update the App (REQUIRED)

Go to:

```text
/app/screens/AudioControlScreen.tsx
```

Find this line:

```ts
{
  uri: "http://192.168.12.163:3000/audio";
}
```

---

## REPLACE WITH YOUR IP

Example:

```ts
{
  uri: "http://192.168.1.45:3000/audio";
}
```

-> If you don’t do this, audio WILL NOT WORK
Please keep a log of IP addresses so you don't have to manually change every time you pull code locally

Souzen: http://192.168.12.163:3000
Fatima:
Maryam:
Ngozi:
Avni:

---

# STEP 3 — Run the Server

Go to server folder:

```bash
cd server
npm install
node server.js
```

You should see:

```text
Server running on port 3000
```

---

# STEP 4 — Run the App

```bash
cd app
npm install
npx expo start
```

---

## IMPORTANT: Use LAN Mode

In Expo:

- Press `shift + l` OR
- Select **LAN** (NOT Tunnel)

---

# 📶 STEP 5 — Same WiFi

Make sure:

```text
Laptop → same WiFi
Phone  → same WiFi
```

NOT:

- mobile data
- guest WiFi
- VPN

---

# STEP 6 — Test Connection (BEFORE APP)

On your phone browser, open:

```text
http://YOUR_IP:3000/audio
```

Example:

```text
http://192.168.12.163:3000/audio
```

You should hear audio

---

# STEP 7 — Use the App

1. Open app on phone
2. Go to **Audio Control screen**
3. Press **Play**
4. Audio should play
5. Press **Pause** to stop

---

# TROUBLESHOOTING

## No audio in app

- Check IP is correct in code
- Make sure server is running
- Make sure same WiFi

---

## Works on laptop but not phone

👉 IP is wrong OR Expo is in Tunnel mode

---

## No sound on iPhone

- Turn OFF silent mode
- Increase volume
- Restart Expo

---

# WHAT THIS MODULE DOES

```text
Server → sends audio file → over WiFi → app → plays audio
```

---

# CURRENT LIMITATION

- Uses audio file (not live mic yet)
- ~200–500ms startup delay
- No real-time streaming yet

---

# FUTURE WORK

- Live microphone streaming
- WebRTC (low latency)
- Bluetooth routing to hearing aids

---

# OWNER

Souzen — Networking & Streaming
