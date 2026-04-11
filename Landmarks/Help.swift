//
//  Help.swift
//  Landmarks
//
//  Created by Ken Samel on 3/25/2023.
//  Copyright © 2023 Ken Samel. All rights reserved.
//
//  Wednesday, April 8, 2026 @ 12:20:34 
//

import SwiftUI

struct Help: View {
    var body: some View {
        print("Entering Help")
        
        return VStack {
            Group {
                Image("Girl")
                    .resizable()
//                    .aspectRatio(contentMode: .fit)
                    .frame(width: 90, height: 90)
                
                Text("Say What?")
                    .font(.title)
                    .bold()
                    .italic()
//                    .padding()
//                Spacer()
                    .position(x: 190, y: 4)    // was 20
            }
            
            Group {
                Text("Be sure your mobile device is connected to the venue's WiFi network.")
                    .font(.system(size: 18))
                    .bold()
                    .frame(width: 320, alignment: .topLeading)
                Spacer()
                Spacer()
                Text("You can check this by going to Settings > Wi-Fi on this device and verify you are connected.")
                    .font(.system(size: 18))
                    .bold()
                    .frame(width: 320, alignment: .topLeading)
                Spacer()
                Spacer()
                Text("If you are not connected, locate the venue's WiFi ID and select it.")
                    .font(.system(size: 18))
                    .bold()
                    .frame(width: 320, alignment: .topLeading)
                Spacer()
                Spacer()
                Text("Or go to the Setup Screen in this app and enter the venue's information.")
                    .font(.system(size: 18))
                    .bold()
                    .frame(width: 320, alignment: .topLeading)
            }
            
            Spacer()
            Text("---------------")
                .font(.system(size: 18))
                .bold()
            Spacer()

            Group {
                Text("Confirm your Bluetooth hearing aids / earbuds are paired to your mobile device.")
                    .font(.system(size: 18))
                    .bold()
                    .frame(width: 320, alignment: .topLeading)
                Spacer()
                Spacer()
                Text("You can check this by going to Settings > Bluetooth and verifying they are paired.")
                    .font(.system(size: 18))
                    .bold()
                    .frame(width: 320, alignment: .topLeading)
                Spacer()
                Spacer()
                Spacer()
                Spacer()
                Spacer()
                Spacer()
//                    .position(x: 190, y: 25)
            }
            
// Copyright statement
            
            Text("Copyright: Two Brothers 😀, LLC")
                .bold()
//                .lineLimit(nil)
        }
        .navigationBarTitle("Help", displayMode: .inline)
    }
}

struct Help_Previews: PreviewProvider {
    static var previews: some View {
        Help()
    }
}

