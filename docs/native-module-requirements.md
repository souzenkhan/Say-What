Native dependencies (likely needed):

- Bluetooth (device connection / pairing)
- Audio playback / routing (especially with Bluetooth + background audio)
- Camera / QR scanning
- iOS + Android permissions for the above

What does NOT need native code:

- Navigation
- UI screens (home, audio, bluetooth)
- Buttons / layouts

Notes for iOS + Android leads:

- Bluetooth behavior and permissions differ across iOS and Android
- Audio handling (background, routing) may need platform-specific setup
- Camera access required for QR scanning on both platforms

Decision:
We can keep the current app fully in React Native for now, but Bluetooth, audio features, and QR scanning will likely need native modules later.