//
//  SayWhat.swift
//  Landmarks
//
//  Created by Ken Samel on 3/25/2023.
//  Copyright © 2023 Ken Samel. All rights reserved.
//
//  Wednesday, April 8, 2026 @ 12:20:12 
//

import SwiftUI

struct SayWhat: View {
    let image = Image("Girl")
    
    @State private var showConnected = false
    
    var body: some View {
        VStack {
            Group {
                Image("Girl")
                    .resizable()
//                    .aspectRatio(contentMode: .fit)
                    .frame(width: 90, height: 90)
                
                Text("Say What?")
                    .font(.title)
                    .bold()
                    .italic()
                Spacer()
                    .position(x: 190, y: 7)
//                Spacer()
//                Spacer()
            }
            Group {
                Text("While at a venue you can now use your Bluetooth enabled hearing aids / earbuds or wired earbuds to clearly hear the event.")
                    .font(.system(size:18))
                    .bold()
                    .frame(width:320, alignment: .topLeading)
//                    .position(x: 210, y: 1)
                Spacer()
                Spacer()
                Text("You do this by connecting your phone to the venue's WiFi Network.")
                    .font(.system(size:18))
                    .bold()
                    .frame(width:320, alignment: .topLeading)
                Spacer()
                Spacer()
                Text("To connect to the audio you must enter the venue's Network information.")
                    .font(.system(size:18))
                    .bold()
                    .frame(width:320, alignment: .topLeading)
//                    .position(x: 210, y: 8)
                Spacer()
                Spacer()
                Spacer()
                Text("To enter the information touch the Setup button below.")
                    .font(.system(size:18))
                    .bold()
                    .frame(width:320, alignment: .topLeading)

//                    .position(x: 210, y: 5)
                

                if showConnected == false {
                    Text("Not Connected To Venue's Audio Stream")
                        .font(.system(size:18))
                        .bold()
                        .padding()
                        .background(Color.yellow)
                        .position(x: 198, y:50) // was 210
                }
                
                if showConnected == true {
                    Text("Connected To Venue's Audio Stream")
                        .font(.system(size:18))
                        .bold()
                        .padding()
                        .background(Color.yellow)
                        .position(x: 195, y: 50)
                }
            }
            
// Setup, Help & About screen buttons
            
            HStack(spacing:60) {
//                Spacer()
                NavigationLink(destination: Setup()){
                    Text("Setup")}
                    .font(.system(size:20))
                    .foregroundColor(.black)
//                    .position(x: 100, y: 40)
  //              Spacer()
                NavigationLink(destination: Help()){
                    Text("Help")}
                    .font(.system(size:20))
                    .foregroundColor(.black)
//                    .position(x: 65, y: 40)
  //              Spacer()
                NavigationLink(destination: About()){
                    Text("About")}
                    .font(.system(size:20))
                    .foregroundColor(.black)
//                    .position(x: 40, y: 40)
  //              Spacer()
            }
            
// Copyright statement
            VStack {
                Spacer()
                Text("Copyright: Two Brothers 😀, LLC")
                    .bold()
            }
        }
    }
    
    struct SayWhat_Previews: PreviewProvider {
        static var previews: some View {
            SayWhat()
        }
    }
}
