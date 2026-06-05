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
    
    @State private var waveformHeights: [CGFloat] = [40, 70, 60, 80, 55, 35, 90, 45, 65, 30]
    
    var body: some View {
        VStack(spacing: 0) {
            
            // Top Bar
            HStack {
                Image(systemName: "ear")
                    .foregroundColor(AppTheme.blue)
                
                Spacer()
                
                Text("Say What?")
                    .font(.title2)
                    .fontWeight(.bold)
                    .foregroundColor(AppTheme.blue)
                    .padding(.horizontal, 10)
                    .padding(.vertical, 4)
                    .background(Color.blue.opacity(0.08))
                    .cornerRadius(8)
                
                Spacer()
                
                Image(systemName: "gearshape")
                    .foregroundColor(AppTheme.blue)
            }
            .padding()
            .background(Color.white)
            
            // Main Audio Content
            ScrollView {
                VStack(spacing: 22) {
                    
                    // Live Session Card
                    VStack(alignment: .leading, spacing: 12) {
                        HStack {
                            Text("● LIVE")
                                .font(.caption)
                                .fontWeight(.bold)
                                .foregroundColor(.white)
                                .padding(.horizontal, 12)
                                .padding(.vertical, 8)
                                .background(Color.red)
                                .cornerRadius(20)
                            
                            Text("Session Active")
                                .font(.headline)
                                .foregroundColor(AppTheme.text)
                            
                            Spacer()
                        }
                        
                        Text("Grand Concert Hall")
                            .font(.largeTitle)
                            .fontWeight(.bold)
                            .foregroundColor(AppTheme.text)
                        
                        HStack {
                            Image(systemName: "clock")
                            Text(audioManager.playbackStatus)
                        }
                        .font(.subheadline)
                        .foregroundColor(.gray)
                        
                        // Animated Waveform
                        HStack(alignment: .bottom, spacing: 8) {
                            ForEach(waveformHeights.indices, id: \.self) { index in
                                RoundedRectangle(cornerRadius: 4)
                                    .fill(AppTheme.blue)
                                    .frame(width: 10, height: waveformHeights[index])
                                    .animation(.easeInOut(duration: 0.25), value: waveformHeights[index])
                            }
                        }
                        .frame(maxWidth: .infinity)
                        .padding()
                        .background(AppTheme.background)
                        .cornerRadius(12)
                        .onReceive(
                            Timer.publish(every: 0.35, on: .main, in: .common).autoconnect()
                        ) { _ in
                            if audioManager.isPlaying {
                                waveformHeights = waveformHeights.map { _ in
                                    CGFloat.random(in: 20...95)
                                }
                            }
                        }
                    }
                    .padding()
                    .background(Color.white)
                    .cornerRadius(14)
                    .overlay(
                        RoundedRectangle(cornerRadius: 14)
                            .stroke(Color.gray.opacity(0.25), lineWidth: 1)
                    )
                    .shadow(color: Color.black.opacity(0.06), radius: 6)
                    .padding(.horizontal)
                    
                    // Bluetooth Device Card
                    VStack(alignment: .leading, spacing: 18) {
                        
                        HStack {
                            Image(systemName: "headphones")
                                .font(.title2)
                                .foregroundColor(AppTheme.blue)
                            
                            Text("CONNECTED DEVICE")
                                .font(.caption)
                                .fontWeight(.bold)
                                .foregroundColor(AppTheme.text)
                            
                            Spacer()
                        }
                        
                        Text(audioManager.currentOutput)
                            .font(.title2)
                            .fontWeight(.bold)
                            .foregroundColor(AppTheme.blue)
                        
                        Text(audioManager.currentOutput.lowercased().contains("speaker") || audioManager.currentOutput.lowercased().contains("receiver") ? "No Bluetooth headphones detected." : "Audio is routing through this device.")
                            .font(.caption)
                            .foregroundColor(.gray)
                        
                        HStack(spacing: 20) {
                            Button(action: {
                                audioManager.play()
                            }) {
                                Text("Play")
                                    .fontWeight(.bold)
                                    .foregroundColor(.white)
                                    .frame(maxWidth: .infinity)
                                    .padding()
                                    .background(AppTheme.blue)
                                    .cornerRadius(8)
                            }
                            
                            Button(action: {
                                audioManager.pause()
                            }) {
                                Text("Pause")
                                    .fontWeight(.bold)
                                    .foregroundColor(AppTheme.text)
                                    .frame(maxWidth: .infinity)
                                    .padding()
                                    .background(Color.gray.opacity(0.2))
                                    .cornerRadius(8)
                            }
                        }
                    }
                    .padding()
                    .background(Color.white)
                    .cornerRadius(14)
                    .overlay(
                        RoundedRectangle(cornerRadius: 14)
                            .stroke(Color.gray.opacity(0.25), lineWidth: 1)
                    )
                    .shadow(color: Color.black.opacity(0.06), radius: 6)
                    .padding(.horizontal)
                    
                    // Status Info
                    VStack(spacing: 8) {
                        Text("Connection: \(appState.connectionStatus)")
                            .font(.caption)
                            .foregroundColor(.gray)
                        
                        if audioManager.isBuffering {
                            Text("Buffering...")
                                .font(.caption)
                                .foregroundColor(.orange)
                        }
                    }
                    .padding(.bottom, 20)
                }
                .padding(.top, 25)
            }
            .background(AppTheme.background)
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            
            BottomNavBar(selectedTab: .audio)
        }
        .navigationBarHidden(true)
    }
}

struct ListeningView_Previews: PreviewProvider {
    static var previews: some View {
        ListeningView()
            .environmentObject(AppState())
            .environmentObject(AudioManager())
    }
}
