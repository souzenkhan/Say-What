//
//  Setup.swift
//  Landmarks
//
//  Created by Ken Samel on 3/25/2023.
//  Copyright © 2023 Ken Samel. All rights reserved.
//
//  Wednesday, April 8, 2026 @ 12:20:22 
//

import SwiftUI

struct Setup: View {
  @State var networkID: String = ""
  @State var password: String = ""
  @State var security: String = ""
  @State var scanResult = "No QR code detected"
    
    let image = Image("QR")

    var body: some View {
        print("Entering Setup")

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
                Spacer()
                    .position(x: 190, y: 7)    // was 20
            }
            
// Scan QR Code

            Group {
//                Text("")
                Text("Scan Venue's QR Code")
                    .bold()
                    .underline()
                    .font(.system(size:18))
                Text("(looks like this)")

                NavigationLink(destination: QRScanner(), label: {
                    Image("QR")
                    .resizable()
                    .aspectRatio(contentMode: .fit)
                })

//                Text("")
                Text("^  ^  ^")
                Text("Touch To Scan")
//                Text("")
//                Text("")
//                Text("")
                Spacer()
                Spacer()
            }

            Group {
                Text("Or")
                    .bold()
                    .font(.system(size:18))
                Spacer()
                Spacer()
                Text("Enter Venue's Network Information")
                    .bold()
                    .underline()
                    .font(.system(size:18))
                Spacer()
                Spacer()
            }

// Network ID Entry
            Group {
                Text("Network ID")
                    .bold()
                TextField("                           Required",text: $networkID)
                    .border(Color.black)
                    .frame(width: 300, alignment: .topLeading)
                    .disableAutocorrection(true)
                    .autocapitalization(.none)
                Spacer()
                Spacer()
            }
            
// Network Password Entry

            Group {
                Text("Network Password")
                    .bold()
                TextField("                           Required",text: $password)
                    .border(Color.black)
                    .frame(width: 300, alignment: .topLeading)
                    .disableAutocorrection(true)
                    .autocapitalization(.none)
                Spacer()
                Spacer()
            }
                
// Security Code Entry
            
            Group {
                Text("Venue Security Code")
                    .bold()
                TextField("               Not Always Required",text: $security)
                    .border(Color.black)
                    .frame(width: 300, alignment: .topLeading)
                    .disableAutocorrection(true)
                    .autocapitalization(.none)
                Spacer()
                Spacer()
                Spacer()
//                    .position(x: 40, y: 1)
            }

// Connect button
            
            Group {
                Spacer()
                Spacer()
                Spacer()
                Text("-> Connect <-")
                    .bold()
                    .font(.system(size:20))
                Spacer()
                Spacer()
                Spacer()
//                   .position(x: 190, y: 1)
            }

// Copyright statement

            Text("Copyright: Two Brothers 😀, LLC")
                .bold()
            }
            
        .navigationBarTitle("Setup", displayMode: .inline)
    }
}

struct Setup_Previews: PreviewProvider {
    static var previews: some View {
        Setup()
    }
}
