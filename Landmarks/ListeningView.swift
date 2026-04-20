//
//  ListeningView.swift
//  Landmarks
//
//  Created by Maryam Bouguerrra on 4/19/26.
//  Copyright © 2026 Ken Samel. All rights reserved.
//

import SwiftUI

struct ListeningView: View {

    @EnvironmentObject var audioManager: AudioManager
    @EnvironmentObject var appState: AppState

    var body: some View {

        VStack(spacing: 30) {

            Text("Venue Audio")
                .font(.largeTitle)
                .bold()

            Text(appState.connectionStatus)
                .font(.headline)
            Text("Output Device: \(audioManager.currentOutput)")
                .font(.headline)

            // Play / Pause button

            Button(action: {

                if audioManager.isPlaying {
                    audioManager.pause()
                } else {
                    audioManager.play()
                }

            }) {

                Text(audioManager.isPlaying ? "Pause" : "Play")
                    .font(.title2)
                    .bold()
                    .padding()
                    .frame(width: 200)
                    .background(Color.blue)
                    .foregroundColor(.white)
                    .cornerRadius(10)

            }

            // Volume slider

            VStack {

                Text("Volume")

                Slider(
                    value: Binding(
                        get: { Double(audioManager.volume) },
                        set: { audioManager.setVolume(Float($0)) }
                    ),
                    in: 0...1
                )

            }
            .padding()

            Spacer()

        }
        .padding()
    }
}

struct ListeningView_Previews: PreviewProvider {
    static var previews: some View {
        ListeningView()
            .environmentObject(AppState())
            .environmentObject(AudioManager())
    }
}
