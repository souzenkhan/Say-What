import Foundation
import Combine

final class AppState: ObservableObject {
    @Published var isConnected: Bool = false
    @Published var streamURLString: String = ""
    @Published var venueName: String = ""
    @Published var connectionStatus: String = "Not Connected To Venue's Audio Stream"
}