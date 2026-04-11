//
//  About.swift
//  Landmarks
//
//  Created by Ken Samel on 3/25/2023.
//  Copyright © 2023 Ken Samel. All rights reserved.
//
//  Wednesday, April 8, 2026 @ 12:20:53 
//

import SwiftUI

struct About: View {
    var body: some View {
        print("Entering About")

        return VStack {
        Group {
            Image("Girl")
                .resizable()
//                .aspectRatio(contentMode: .fit)
                .frame(width: 90, height: 90)

            Text("Say What?")
                .font(.title)
                .bold()
                .italic()
            Spacer()
            Spacer()
            Spacer()
                .position(x: 190, y: 4)
        }
        Group {
            Text("Say What? was created to help resolve a problem many of us encounter on a regular basis.")
                .font(.system(size: 20))
                .bold()
                .frame(width: 320, alignment: .topLeading)
                .position(x: 210, y: 1)

            Text("You will now be able to use your personal Bluetooth enabled hearing aids / earbuds or wired earbuds to clearly hear the audio at the venue.")
                .font(.system(size: 20))
                .bold()
                .frame(width: 320, alignment: .topLeading)
                .position(x: 210, y: 1)
//            Spacer()
//            Spacer()

            Text("You can also use the volume controls of your mobile device to adjust the volume and ensure you can comfortably hear the venue's audio.")
                .font(.system(size:20))
                .bold()
                .frame(width: 320, alignment: .topLeading)
                .position(x: 210, y: 1)
            Spacer()
            Spacer()
            Spacer()
        }

// Copyright statement

            Text("Copyright: Two Brothers 😀, LLC")
                .bold()
        }
        .navigationBarTitle("About", displayMode: .inline)
    }
}

struct About_Previews: PreviewProvider {
    static var previews: some View {
        About()
    }
//    print("Leaving About")

}
