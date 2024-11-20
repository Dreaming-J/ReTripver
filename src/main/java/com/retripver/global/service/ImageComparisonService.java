package com.retripver.global.service;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;

import org.apache.commons.io.IOUtils;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ImageComparisonService {
	
	@Value("${opencv.similarity-threshold}")
	private double threshold;

    public boolean compareTwoImages(String imageUrl1, String imageUrl2) {
		try {
	        Mat img1 = downloadAndReadImage(imageUrl1);
	        Mat img2 = downloadAndReadImage(imageUrl2);
	        
	        double similarity = calculateSimilarity(img1, img2);
	        
	        System.out.println("유사도: " + similarity);
	        
	        return calculateSimilarity(img1, img2) >= threshold;
		} catch (IOException e) {
			throw new RuntimeException();
		}
    }

    private Mat downloadAndReadImage(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        byte[] imageBytes = IOUtils.toByteArray(url);
        MatOfByte mob = new MatOfByte(imageBytes);
        
        return Imgcodecs.imdecode(mob, Imgcodecs.IMREAD_COLOR);
    }

    private double calculateSimilarity(Mat img1, Mat img2) {
        // Convert to grayscale
        Mat gray1 = new Mat();
        Mat gray2 = new Mat();
        
        Imgproc.cvtColor(img1, gray1, Imgproc.COLOR_BGR2GRAY);
        Imgproc.cvtColor(img2, gray2, Imgproc.COLOR_BGR2GRAY);
        


        Mat hist_1 = new Mat();
        Mat hist_2 = new Mat();

        MatOfFloat ranges = new MatOfFloat(0f, 256f);
        MatOfInt histSize = new MatOfInt(25);

        Imgproc.calcHist(Collections.singletonList(gray1), new MatOfInt(0),
                new Mat(), hist_1, histSize, ranges);
        Imgproc.calcHist(Collections.singletonList(gray2), new MatOfInt(0),
                new Mat(), hist_2, histSize, ranges);

        return Imgproc.compareHist(hist_1, hist_2, Imgproc.CV_COMP_CORREL);

//        // Initialize ORB detector
//        ORB orb = ORB.create();
//        
//        // Detect keypoints and compute descriptors
//        MatOfKeyPoint keypoints1 = new MatOfKeyPoint();
//        MatOfKeyPoint keypoints2 = new MatOfKeyPoint();
//        Mat descriptors1 = new Mat();
//        Mat descriptors2 = new Mat();
//        
//        orb.detectAndCompute(gray1, new Mat(), keypoints1, descriptors1);
//        orb.detectAndCompute(gray2, new Mat(), keypoints2, descriptors2);
//
//        if (descriptors1.empty() || descriptors2.empty()) {
//            return 0.0;
//        }
//        
//        // Match features
//        BFMatcher matcher = BFMatcher.create(Core.NORM_HAMMING, true);
//        MatOfDMatch matches = new MatOfDMatch();
//        matcher.match(descriptors1, descriptors2, matches);
//
//        double maxDist = 50.0;
//        int goodMatches = 0;
//        
//        for (DMatch match : matches.toArray()) {
//            if (match.distance < maxDist) {
//                goodMatches++;
//            }
//        }
//
//        return (double) goodMatches / matches.total();
    }
}
