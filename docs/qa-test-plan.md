# Say What? Initial QA Test Plan

## Purpose

The primary Say What? app features will be tested prior to Week 2 testing, according to this QA test plan.

Ensuring that the basic screens, navigation, setup flow, accessibility assistance, and error states function as intended is the aim.

## Test Environment

- App: Say What?
- Platform: iOS
- Test device: iPhone Simulator or physical iPhone
- Project: Landmarks / Say What?
- Tester: Team member
- Testing phase: Initial QA before Week 2 testing

## Core Features To Test

- Home screen
- Setup screen
- QR scanner screen
- Help screen
- About screen
- Manual venue information entry
- Connection status message
- Error handling
- Accessibility basics

## Test Checklist

### 1. App Launch

- [ ] App opens without crashing.
- [ ] Home screen loads first.
- [ ] Say What? title is visible.
- [ ] App image/logo is visible.
- [ ] Main explanation text is readable.
- [ ] Connection status message is visible.

Expected result:

The user should see the Say What? home screen and understand what the app is for.

### 2. Home Screen Navigation

- [ ] Tapping Setup opens the Setup screen.
- [ ] Tapping Help opens the Help screen.
- [ ] Tapping About opens the About screen.
- [ ] User can return back to the home screen from each screen.

Expected result:

All main navigation links should work correctly.

### 3. Setup Screen

- [ ] Setup screen opens without crashing.
- [ ] QR scan option is visible.
- [ ] Network ID field is visible.
- [ ] Network Password field is visible.
- [ ] Venue Security Code field is visible.
- [ ] Connect option is visible.
- [ ] User can type into the Network ID field.
- [ ] User can type into the Network Password field.
- [ ] User can type into the Venue Security Code field.

Expected result:

The user should be able to see and enter venue setup information.

### 4. QR Scanner

- [ ] Tapping the QR code image opens the QR scanner screen.
- [ ] Camera preview appears if camera access is available.
- [ ] App does not crash if camera is unavailable.
- [ ] User can return from the QR scanner screen.

Expected result:

The QR scanner screen should open and allow the user to attempt scanning.

### 5. Help Screen

- [ ] Help screen opens without crashing.
- [ ] Wi-Fi instructions are visible.
- [ ] Bluetooth/hearing aid instructions are visible.
- [ ] Text is readable.
- [ ] User can return to the previous screen.

Expected result:

The user should be able to find guidance for Wi-Fi and Bluetooth setup.

### 6. About Screen

- [ ] About screen opens without crashing.
- [ ] App purpose text is visible.
- [ ] Text explains who the app helps.
- [ ] Copyright text is visible.
- [ ] User can return to the previous screen.

Expected result:

The user should understand why the app exists and who it is designed for.

### 7. Connection Status

- [ ] Home screen shows Not Connected status by default.
- [ ] Connected status design is planned or available.
- [ ] Status message is readable.
- [ ] Status is shown as text, not only color.

Expected result:

The user should always be able to tell whether they are connected.

### 8. Error Handling

- [ ] App should handle missing Network ID.
- [ ] App should handle missing password.
- [ ] App should handle incorrect venue information.
- [ ] App should handle QR scan failure.
- [ ] App should handle camera permission denial.
- [ ] Error messages should explain what the user can do next.

Expected result:

The user should receive clear guidance when something goes wrong.

### 9. Accessibility Checks

- [ ] Text is readable on all main screens.
- [ ] Buttons are clearly labeled.
- [ ] App does not rely on sound-only instructions.
- [ ] Connection status is visible as text.
- [ ] Manual entry is available if QR scanning does not work.
- [ ] Help screen supports users who need setup guidance.

Expected result:

Users with hearing impairments should be able to utilize the program, and it should offer clear visual instructions.

## Known Limitations

- It's possible that the Connect action isn't working properly yet.
- Scanned data may not yet be fully processed by QR scanning.
- It's possible that the audio streaming connection hasn't been implemented yet.
- Until those functionalities are developed, certain tests can be marked as planned.

## Week 2 Testing Readiness

The app is ready for Week 2 testing when:

- [ ] Main screens can be opened.
- [ ] Navigation works between screens.
- [ ] Setup fields can accept text.
- [ ] QR scanner screen can be opened.
- [ ] Help and About content are readable.
- [ ] Known limitations are documented.
- [ ] Testers know which features are working and which are still planned.
