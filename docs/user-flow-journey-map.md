Say What? User Flow + Journey Map

#1. Purpose

Say What? enhances the clarity of event audio for attendees using wired headphones, Bluetooth earbuds, or their own hearing aids.
By manually entering the venue's network details or scanning a QR code, the software provides users with an easy way to connect to the audio system of the venue.

#2. Primary User
A person attending an event who has trouble clearly hearing the venue audio is the main user.
This user wishes to listen to the event audio directly through their own device and may utilize wired headphones, Bluetooth earbuds, or hearing aids.

#3. Main User Flow

1. The user launches the Say What? application.
2. The user arrives at the home screen.
3. A brief description of the app's functionality is read by the user.
4. The user observes that they are not linked to the audio stream from the venue.
5. The Setup button is tapped by the user.
6. The user can either manually enter the venue's network information or scan the QR code.
7. The user has the option to manually enter the data or scan the QR code.
8. When scanning, the user enables camera access by tapping the QR picture.
9. The app reads the venue connection information by scanning the QR code.
10. The network ID, network password, and optional venue security code are entered manually by the user.
11. The user selects Connect.
12. The application verifies that the necessary data has been input.
13. The software makes an effort to join the audio stream from the venue.
14. The application displays the user's connection status.
15. After connecting, the user uses headphones, earbuds, or hearing aids to listen to the venue audio.

#4. Alternate Flows

- Help Flow
1. The user launches the Say What? application.
2. The user selects Help.
3. The user reads directions on how to connect their mobile device to the Wi-Fi at the location.
4. The user reads instructions on how to check Bluetooth earbuds or hearing aids.
5. The user goes back to either the home screen or the Setup screen.

- About Flow
1. The user launches the Say What? application.
2. The user selects About.
3. The user reads the motivation for the app's creation.
4. The user discovers that the app's purpose is to improve venue audio clarity.
5. The user goes back to the main screen.

#5. Error Cases

- QR Code Does Not Scan
The app should display a clear message and let the user manually enter the venue details if the QR code fails to scan.

- Camera Permission Is Denied
The app should inform that camera access is required for QR scanning if the user rejects camera permission, but it should still permit manual setup.

- Missing Network ID
The application should prompt the user to enter the necessary network ID if they attempt to connect without doing so.

- Missing Or Incorrect Password
The app should provide a notification stating that the password has to be verified and typed again if it is absent or wrong.

- Missing Security Code
The app should prompt the user to provide the venue security code if it is required and they fail to do so.

- Audio Stream Is Unavailable
In the event that the venue audio feed is unavailable, the app ought immediately notify the user and let them try again.

- Hearing Aids Or Earbuds Are Not Connected
The app should recommend checking the Bluetooth settings or wired headphone connection if the user's earbuds or hearing aids are not connected.

6. Journey Map

Discover
- User goal: Understand what the app does.
- User action: Opens the app.
- App response: Shows the app purpose and current connection status.
- User feeling: Curious.

Prepare
- User goal: Start the setup process.
- User action: Taps Setup.
- App response: Shows QR scan and manual entry options.
- User feeling: Ready.

Enter Info
- User goal: Provide the venue connection details.
- User action: Scans the QR code or types the network information.
- App response: Reads or saves the connection details.
- User feeling: Focused.

Connect
- User goal: Join the venue audio stream.
- User action: Taps Connect.
- App response: Checks the information and attempts to connect.
- User feeling: Hopeful.

Listen
- User goal: Hear the event clearly.
- User action: Uses hearing aids, earbuds, or headphones.
- App response: Plays venue audio through the user's device.
- User feeling: Comfortable.

Recover
- User goal: Fix a problem if something goes wrong.
- User action: Opens Help or retries setup.
- App response: Shows Wi-Fi, Bluetooth, or manual entry guidance.
- User feeling: Supported.

#7. Open Questions

1. What exact information will be stored inside the venue QR code?
2. Will the app connect to the venue's Wi-Fi, an audio stream, or both?
3. What audio streaming format will the venue use?
4. Should the app save previous venue connection information?
5. Should the app support larger text for accessibility?
6. Should the app support multiple languages?
7. What should happen if the venue has multiple audio channels?
8. Should users be able to test the audio before the event starts?
