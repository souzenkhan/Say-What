//
//  QRScanner.swift
//  Landmarks
//
//  Created by Ken Samel on 4/26/23.
//  Copyright © 2023 Ken Samel. All rights reserved.
//
//  Thursday, May 11, 2023 @ 12:04:30
//

import SwiftUI
import AVFoundation

struct QRScannerView: View {
    var body: some View {
        VStack(spacing: 0) {

            // Top Bar
            HStack {
                Image(systemName: "ear")
                    .foregroundColor(AppTheme.blue)

                Spacer()

                Text("Say What?")
                    .font(.title2)
                    .bold()
                    .foregroundColor(AppTheme.blue)

                Spacer()

                Image(systemName: "gearshape")
                    .foregroundColor(AppTheme.blue)
            }
            .padding()
            .background(Color.white)

            VStack(spacing: 28) {
                Text("Scan QR Code")
                    .font(.title)
                    .bold()
                    .foregroundColor(AppTheme.text)
                    .padding(.top, 25)

                Text("Point camera at venue QR code to\nconnect automatically.")
                    .font(.body)
                    .multilineTextAlignment(.center)
                    .foregroundColor(AppTheme.text)

                ZStack {
                    RoundedRectangle(cornerRadius: 20)
                        .fill(Color.gray.opacity(0.55))
                        .frame(width: 320, height: 320)

                    QRScanner()
                        .frame(width: 250, height: 250)
                        .cornerRadius(8)
                        .overlay(
                            RoundedRectangle(cornerRadius: 8)
                                .stroke(AppTheme.blue, lineWidth: 3)
                        )
                }

                NavigationLink(destination: Setup()) {
                    HStack {
                        Image(systemName: "qrcode.viewfinder")
                        Text("Use Scan")
                            .bold()
                    }
                    .foregroundColor(.white)
                    .frame(maxWidth: .infinity)
                    .padding()
                    .background(AppTheme.blue)
                    .cornerRadius(10)
                }
                .padding(.horizontal, 24)

                NavigationLink(destination: SayWhat()) {
                    HStack {
                        Image(systemName: "arrow.left")
                        Text("Back")
                            .bold()
                    }
                    .foregroundColor(AppTheme.blue)
                    .frame(maxWidth: .infinity)
                    .padding()
                    .overlay(
                        RoundedRectangle(cornerRadius: 10)
                            .stroke(AppTheme.blue, lineWidth: 1.5)
                    )
                }
                .padding(.horizontal, 24)
            }

            Spacer()

            BottomNavBar(selectedTab: .scan)
        }
        .background(AppTheme.background)
        .navigationBarHidden(true)
    }
}

struct QRScanner: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> QRScannerController {
        return QRScannerController()
    }

    func updateUIViewController(_ uiViewController: QRScannerController, context: Context) {
    }
}

class QRScannerController: UIViewController {
    var captureSession = AVCaptureSession()
    var videoPreviewLayer: AVCaptureVideoPreviewLayer?
    var qrCodeFrameView: UIView?
    var delegate: AVCaptureMetadataOutputObjectsDelegate?

    override func viewDidLoad() {
        super.viewDidLoad()

        guard let captureDevice = AVCaptureDevice.default(.builtInWideAngleCamera, for: .video, position: .back) else {
            print("Failed to connect to the camera")
            return
        }

        let videoInput: AVCaptureDeviceInput

        do {
            videoInput = try AVCaptureDeviceInput(device: captureDevice)
        } catch {
            print(error)
            return
        }

        captureSession.addInput(videoInput)

        let captureMetadataOutput = AVCaptureMetadataOutput()
        captureSession.addOutput(captureMetadataOutput)

        captureMetadataOutput.setMetadataObjectsDelegate(delegate, queue: DispatchQueue.main)
        captureMetadataOutput.metadataObjectTypes = [.qr]

        videoPreviewLayer = AVCaptureVideoPreviewLayer(session: captureSession)
        videoPreviewLayer?.videoGravity = AVLayerVideoGravity.resizeAspectFill
        videoPreviewLayer?.frame = view.layer.bounds

        if let videoPreviewLayer = videoPreviewLayer {
            view.layer.addSublayer(videoPreviewLayer)
        }

        DispatchQueue.global(qos: .background).async {
            self.captureSession.startRunning()
        }
    }
}
