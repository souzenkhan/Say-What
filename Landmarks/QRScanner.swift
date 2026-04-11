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

struct QRScanner: UIViewControllerRepresentable {
    
    func makeUIViewController(context: Context) -> QRScannerController {
        let controller = QRScannerController()
        
        return controller
    }
    
    func updateUIViewController(_ uiViewController: QRScannerController, context: Context) {
    }
}

class QRScannerController:UIViewController {
    var captureSession = AVCaptureSession()
    var videoPreviewLayer: AVCaptureVideoPreviewLayer?
    var qrCodeFrameView: UIView?
    
    var delegate: AVCaptureMetadataOutputObjectsDelegate?

    override func viewDidLoad() {
        super.viewDidLoad()
        
// Get the back-facing camera for capturing the QR Code
        
        guard let captureDevice = AVCaptureDevice.default(.builtInWideAngleCamera, for: .video, position: .back) else {
            print("Failed to connect to the camera")
            return
        }
        
        let videoInput: AVCaptureDeviceInput
        
        do {

// Get an instance of the AVcaptureDeviceInput class using the previous device object

            videoInput = try AVCaptureDeviceInput(device: captureDevice)
            
        } catch {

// If an error occurs, simply print it out and don't continue

            print(error)
            return
        }

// Set the input device on the capture session

        captureSession.addInput(videoInput)
        
// Initialze an AVCaptureMetadataOutput object and set it as the output device to the capture session

        let captureMetadataOutput = AVCaptureMetadataOutput()
        captureSession.addOutput(captureMetadataOutput)
        
// Set delegate and use the default dispatch queue to execute the call back

        captureMetadataOutput.setMetadataObjectsDelegate(delegate, queue: DispatchQueue.main)
        captureMetadataOutput.metadataObjectTypes = [ .qr ]
        
// Initialize the video preview layer and add it as a sublayer to the viewPreview view's layer

        videoPreviewLayer = AVCaptureVideoPreviewLayer(session: captureSession)
        videoPreviewLayer?.videoGravity = AVLayerVideoGravity.resizeAspectFill
        videoPreviewLayer?.frame = view.layer.bounds
        view.layer.addSublayer(videoPreviewLayer!)
        
// Start video capture

        DispatchQueue.global(qos: .background).async {
            self.captureSession.startRunning()
            
        }
    }
}
