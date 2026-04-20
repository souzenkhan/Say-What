import Foundation
import AVFoundation
import Combine

final class AudioManager: ObservableObject {
    @Published var isPlaying: Bool = false
    @Published var isBuffering: Bool = false
    @Published var volume: Float = 1.0
    @Published var errorMessage: String = ""
    @Published var currentOutput: String = "Unknown"
    
    private var player: AVPlayer?

    init() {
        configureAudioSession()

        NotificationCenter.default.addObserver(
            self,
            selector: #selector(handleRouteChange),
            name: AVAudioSession.routeChangeNotification,
            object: nil
        )

        updateCurrentRoute()
    }

    func configureAudioSession() {
        do {
            let session = AVAudioSession.sharedInstance()
            try session.setCategory(.playback, mode: .default, options: [.allowBluetooth, .allowAirPlay])
            try session.setActive(true)
        } catch {
            errorMessage = "Failed to configure audio session: \(error.localizedDescription)"
            print(errorMessage)
        }
    }

    func loadStream(from urlString: String) {
        guard let url = URL(string: urlString) else {
            errorMessage = "Invalid stream URL"
            return
        }

        player = AVPlayer(url: url)
        player?.volume = volume
    }

    func play() {
        guard let player = player else {
            errorMessage = "No audio stream loaded"
            return
        }

        player.play()
        isPlaying = true
        updateCurrentRoute()
    }

    func pause() {
        player?.pause()
        isPlaying = false
    }

    func stop() {
        player?.pause()
        player?.seek(to: .zero)
        isPlaying = false
    }

    func setVolume(_ newVolume: Float) {
        volume = newVolume
        player?.volume = newVolume
    }
    
    @objc func handleRouteChange(notification: Notification) {
        updateCurrentRoute()
    }

    func updateCurrentRoute() {
        let session = AVAudioSession.sharedInstance()
        let outputs = session.currentRoute.outputs

        if let output = outputs.first {
            currentOutput = output.portName
        } else {
            currentOutput = "No Output Device"
        }
    }
}
