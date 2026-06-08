//
//  FeatureCard.swift
//  Landmarks
//
//  Created by Maryam Bouguerrra on 6/2/26.
//  Copyright © 2026 Ken Samel. All rights reserved.
//

import SwiftUI

struct FeatureCard: View {
    let icon: String
    let title: String
    let description: String

    var body: some View {
        VStack(alignment: .leading, spacing: 14) {
            Image(systemName: icon)
                .font(.system(size: 32))
                .foregroundColor(AppTheme.blue)

            Text(title)
                .font(.title2)
                .bold()
                .foregroundColor(AppTheme.text)

            Text(description)
                .font(.body)
                .foregroundColor(AppTheme.text)
                .lineSpacing(5)
        }
        .padding(28)
        .frame(maxWidth: .infinity, alignment: .leading)
        .background(Color.white)
        .cornerRadius(12)
        .shadow(color: Color.black.opacity(0.06), radius: 12, x: 0, y: 6)
        .padding(.horizontal, 22)
    }
}
