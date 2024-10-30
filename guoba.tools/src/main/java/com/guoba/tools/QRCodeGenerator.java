package com.guoba.tools;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class QRCodeGenerator {
    public static void generateQRCodeImage(String text, int width, int height, String filePath, String logoPath)
        throws WriterException, IOException {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hints);

        BufferedImage qrImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D qrGraphics = qrImage.createGraphics();
        qrGraphics.drawImage(MatrixToImageWriter.toBufferedImage(bitMatrix), 0, 0, null);
        qrGraphics.dispose();

        BufferedImage logoImage = ImageIO.read(new File(logoPath));

        int logoWidth = qrImage.getWidth() / 6;
        int logoHeight = qrImage.getHeight() / 6;
        int logoX = (qrImage.getWidth() - logoWidth) / 2;
        int logoY = (qrImage.getHeight() - logoHeight) / 2;

        BufferedImage resizedLogo = new BufferedImage(logoWidth, logoHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedLogo.createGraphics();
        g2d.drawImage(logoImage, 0, 0, logoWidth, logoHeight, null);
        g2d.dispose();

        Graphics2D graphics = qrImage.createGraphics();
        graphics.drawImage(resizedLogo, logoX, logoY, null);
        graphics.dispose();

        Path path = Paths.get(filePath);
        ImageIO.write(qrImage, "PNG", path.toFile());
    }

    public static void main(String[] args) {
        String text = "https://goodie.norriszhang.com/signup";
        int width = 300;
        int height = 300;
        String filePath = "/Users/norris/Downloads/qrcode.png";
        String logoPath = "/Users/norris/Documents/personal/projects/goodie/qr-signup-child-face.png";

        try {
            generateQRCodeImage(text, width, height, filePath, logoPath);
            System.out.println("QR code generated and saved to " + filePath);
        } catch (Exception e) {
            System.err.println("Error generating QR Code: " + e.getMessage());
        }
    }
}
