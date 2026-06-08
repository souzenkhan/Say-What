import SwiftUI

struct SayWhat: View {
    @EnvironmentObject var appState: AppState

    var body: some View {
        VStack(spacing: 0) {

            // Top bar
            HStack {
                Image(systemName: "ear")
                    .foregroundColor(AppTheme.blue)

                Spacer()

                NavigationLink(destination: About()) {
                    Text("Say What?")
                        .font(.headline)
                        .fontWeight(.bold)
                        .foregroundColor(AppTheme.blue)
                        .padding(.horizontal, 10)
                        .padding(.vertical, 4)
                        .background(Color.blue.opacity(0.08))
                        .cornerRadius(8)
                }

                Spacer()

                Image(systemName: "gearshape")
                    .foregroundColor(AppTheme.blue)
            }
            .padding()
            .background(Color.white)

            ScrollView {
                VStack(spacing: 28) {

                    Text("Hearing clearly\nshouldn't be a luxury.")
                        .font(.title3)
                        .fontWeight(.bold)
                        .multilineTextAlignment(.center)
                        .foregroundColor(AppTheme.blue)
                        .padding(.top, 30)

                    Text("Say What? was born from a simple observation: public spaces are often designed for aesthetics, not acoustics. Our mission is to bridge the communication gap for those with hearing challenges using advanced real-time audio processing.")
                        .font(.body)
                        .multilineTextAlignment(.center)
                        .foregroundColor(AppTheme.text)
                        .lineSpacing(5)
                        .padding(.horizontal, 20)

                    Image("hearingMan")
                        .resizable()
                        .scaledToFit()
                        .frame(width: 280, height: 280)
                        .background(Color.white)
                        .cornerRadius(14)
                        .padding(.horizontal, 24)
                    
                    Button(action: {}) {
                            Text("NEXT")
                                .font(.caption)
                                .fontWeight(.bold)
                                .foregroundColor(.white)
                                .padding(.horizontal, 22)
                                .padding(.vertical, 10)
                                .background(AppTheme.blue)
                                .cornerRadius(8)
                        }
                    
                    
                    Text("Ready to Listen?")
                        .font(.title2)
                        .fontWeight(.bold)
                        .foregroundColor(AppTheme.text)

                    Text("Connect to the venue audio by clicking on the button below.")
                        .font(.body)
                        .multilineTextAlignment(.center)
                        .foregroundColor(AppTheme.text)
                        .padding(.horizontal, 35)

                    NavigationLink(destination: Setup()) {
                        HStack {
                            Image(systemName: "qrcode.viewfinder")
                            Text("SCAN QR CODE")
                                .fontWeight(.bold)
                        }
                        .foregroundColor(.white)
                        .frame(maxWidth: .infinity)
                        .padding()
                        .background(AppTheme.blue)
                        .cornerRadius(8)
                        .padding(.horizontal, 24)
                    }

                    VStack(alignment: .leading, spacing: 12) {
                        HStack {
                            Text("Recent Venues")
                                .font(.headline)
                                .foregroundColor(AppTheme.text)

                            Spacer()

                            Text("View All")
                                .font(.caption)
                                .foregroundColor(AppTheme.blue)
                        }

                        VenueRow(title: "City Museum", subtitle: "Visited Yesterday", icon: "building.columns")
                        VenueRow(title: "Starlight Cinema", subtitle: "Visited 3 days ago", icon: "film")
                    }
                    .padding(.horizontal, 24)

                    Button(action: {}) {
                        Text("NEXT")
                            .font(.caption)
                            .fontWeight(.bold)
                            .foregroundColor(.white)
                            .padding(.horizontal, 22)
                            .padding(.vertical, 10)
                            .background(AppTheme.blue)
                            .cornerRadius(8)
                    }
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .padding(.horizontal, 24)

                    VStack(alignment: .leading, spacing: 12) {
                        Text("Nearby Venues")
                            .font(.headline)
                            .foregroundColor(AppTheme.text)

                        VenueRow(title: "St. Jude’s Cathedral", subtitle: "0.2 miles away", icon: "mappin.circle")
                        VenueRow(title: "Olympic Stadium", subtitle: "0.8 miles away", icon: "building.2")
                    }
                    .padding(.horizontal, 24)

                    Spacer(minLength: 30)
                }
            }
            .background(AppTheme.background)
            .frame(maxWidth: .infinity, maxHeight: .infinity)

            BottomNavBar(selectedTab: .home)
        }
        .background(AppTheme.background)
        .navigationBarHidden(true)
    }
}

struct VenueRow: View {
    let title: String
    let subtitle: String
    let icon: String

    var body: some View {
        HStack {
            Image(systemName: icon)
                .foregroundColor(AppTheme.blue)
                .frame(width: 45, height: 45)
                .background(Color.blue.opacity(0.10))
                .cornerRadius(10)

            VStack(alignment: .leading, spacing: 4) {
                Text(title)
                    .font(.subheadline)
                    .fontWeight(.bold)
                    .foregroundColor(AppTheme.text)

                Text(subtitle)
                    .font(.caption)
                    .foregroundColor(.gray)
            }

            Spacer()

            Image(systemName: "chevron.right")
                .font(.caption)
                .foregroundColor(AppTheme.blue)
        }
        .padding()
        .background(Color.white)
        .cornerRadius(10)
        .shadow(color: Color.black.opacity(0.05), radius: 5, x: 0, y: 3)
    }
}

struct SayWhat_Previews: PreviewProvider {
    static var previews: some View {
        SayWhat()
            .environmentObject(AppState())
    }
}
