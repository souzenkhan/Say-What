# Say What? Live User Flow

## Purpose

This document describes the intended live user flow for the Say What? app and notes the current prototype behavior observed in the simulator.

## Intended Live User Flow

1. User opens the Say What? app.
2. User reads the home screen and sees the current connection status.
3. User taps Setup to begin connecting to the venue audio system.
4. User chooses one of two setup methods:
   - Scan the venue QR code
   - Enter venue network information manually
5. User submits the venue information.
6. The app attempts to connect to the venue audio stream.
7. If the connection succeeds, the app shows a connected status.
8. The user listens to venue audio through Bluetooth hearing aids, earbuds, or wired headphones.
9. When finished, the user disconnects.
10. The app returns to a not connected state.

## Intended Error Flow

1. User opens the Say What? app.
2. User starts the setup process.
3. User scans the QR code or enters venue information.
4. The app attempts to connect.
5. If the connection fails, the app shows an error message.
6. The user is prompted to check Wi-Fi, Bluetooth, QR scan input, or venue information.
7. The user retries setup or opens the Help screen.

## Current Prototype Behavior

Based on the current simulator test, the app behaves as follows:

1. Opening the app shows the home screen with the app title, explanatory text, connection status, and Setup, Help, and About options.
2. Tapping Setup opens the Setup screen.
3. The Setup screen shows a QR scan option and fields for Network ID, Network Password, and Venue Security Code.
4. Tapping the QR area opens a blank scanner screen with a back button.
5. The Connect text is currently not a functioning button.
6. A real connected state is not currently implemented.
7. A real disconnect flow is not currently implemented.

## Flow Diagram

```mermaid
flowchart TD
    A["Open App"] --> B["View Home Screen"]
    B --> C["Tap Setup / Connect"]
    C --> D{"Choose Setup Method"}
    D --> E["Scan Venue QR Code"]
    D --> F["Enter Venue Information Manually"]
    E --> G["Read Venue Connection Information"]
    F --> G
    G --> H["Attempt Connection to Venue Audio Stream"]
    H --> I{"Connection Successful?"}
    I -->|Yes| J["Show Connected Status"]
    J --> K["User Listens Through Hearing Aids / Earbuds / Headphones"]
    K --> L["User Disconnects"]
    L --> M["Return to Not Connected State"]
    I -->|No| N["Show Error Message"]
    N --> O["Prompt User to Check Wi-Fi, Bluetooth, or Venue Information"]
    O --> P{"Retry?"}
    P -->|Yes| C
    P -->|No| Q["Open Help Screen"]
