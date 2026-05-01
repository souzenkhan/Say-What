# Say What? Test Plan

## Purpose

This test plan defines simple and reusable testing scenarios for the Say What? app. It focuses on the main live user flow and key connection-related cases.

## Test Scenarios

### 1. Connect Success

**Goal:** Verify that a user can successfully connect to the venue audio stream.

**Steps:**
1. Open the Say What? app.
2. From the home screen, tap Setup or Connect.
3. Choose a setup method:
   - Scan the venue QR code, or
   - Enter venue information manually.
4. Submit valid venue connection information.
5. Wait for the app to attempt connection.

**Expected Result:**
- The app connects successfully.
- A connected status message appears.
- The user can begin listening through hearing aids, earbuds, or headphones.

---

### 2. Connect Failure

**Goal:** Verify that the app handles failed connection attempts clearly.

**Steps:**
1. Open the Say What? app.
2. Start the setup process.
3. Enter incorrect, incomplete, or invalid venue information.
4. Submit the connection attempt.

**Expected Result:**
- The app does not connect.
- An error message appears.
- The user is told to check Wi-Fi, Bluetooth, QR scan result, or venue information.
- The user can retry or open the Help screen.

---

### 3. Disconnect

**Goal:** Verify that the user can disconnect from the venue audio stream.

**Steps:**
1. Open the Say What? app.
2. Connect successfully to the venue audio stream.
3. Start listening through the connected audio device.
4. Choose the disconnect option.

**Expected Result:**
- The app disconnects from the venue audio stream.
- The connection status changes back to not connected.
- The user is no longer in the listening state.

---

### 4. Bluetooth Off

**Goal:** Verify that the app responds appropriately when Bluetooth is turned off.

**Steps:**
1. Turn Bluetooth off on the device.
2. Open the Say What? app.
3. Attempt to connect and listen through Bluetooth hearing aids or earbuds.

**Expected Result:**
- The app informs the user that Bluetooth audio output is unavailable or not connected.
- The app suggests checking Bluetooth settings.
- The user can still review Help instructions or retry after enabling Bluetooth.

## Reusable Checklist

- [ ] App opens successfully.
- [ ] Home screen appears correctly.
- [ ] Setup flow can be started.
- [ ] User can choose QR scan or manual entry.
- [ ] Valid information allows successful connection.
- [ ] Invalid information produces a clear error.
- [ ] Error message gives helpful next steps.
- [ ] User can retry after failure.
- [ ] Connected status appears after success.
- [ ] User can enter listening state after connecting.
- [ ] User can disconnect successfully.
- [ ] Not connected status appears after disconnect.
- [ ] Bluetooth-off case is handled clearly.
- [ ] Help screen is available when needed.

## Notes

- This checklist is intended to be simple and reusable for repeated testing.
- The test plan is based on the intended live user flow of the app.
- Some tests may remain planned until the full connect and disconnect behavior is implemented.
