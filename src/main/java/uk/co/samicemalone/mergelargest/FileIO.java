/*
 * Copyright (c) 2013, Sam Malone. All rights reserved.
 * 
 * Redistribution and use of this software in source and binary forms, with or
 * without modification, are permitted provided that the following conditions
 * are met:
 * 
 *  - Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *  - Neither the name of Sam Malone nor the names of its contributors may be
 *    used to endorse or promote products derived from this software without
 *    specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package uk.co.samicemalone.mergelargest;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFileFilter;

/**
 *
 * @author Sam Malone
 */
public class FileIO {
    
    private boolean isTestMode;

    public FileIO(boolean isTestMode) {
        this.isTestMode = isTestMode;
    }
    
    /**
     * Moves the inputFile to the directory given by destinationDir.
     * If an error occurs whilst moving or a file with inputFile's name already
     * exists in destinationDir, the program will exit, displaying the error
     * message.
     * @param inputFile File to move
     * @param destinationDir Destination Directory
     */
    public void moveFileToDirectory(File inputFile, File destinationDir) {
        System.out.println("Moving: " + inputFile.getName());
        if(!isTestMode) {
            try {
                FileUtils.moveFileToDirectory(inputFile, destinationDir, false);
            } catch (IOException e) {
                System.err.println(e.getMessage());
                System.exit(1);
            }
        }
    }

    /**
     * Delete the directory given by inputDir and all its contents if it does
     * not exist in skipList.
     * @param inputDir Directory to delete
     * @param skipList List of directories to check inputDir against.
     */
    public void deleteDirectory(File inputDir, List<File> skipList) {
        if(skipList.contains(inputDir)) {
            System.out.println("Skipping Deletion of: " + inputDir.getAbsolutePath());
            return;
        }
        System.out.println("Deleting Directory: " + inputDir.getName());
        if(!isTestMode) {
            if(!FileUtils.deleteQuietly(inputDir)) {
                System.out.println("Warning: Unable to delete " + inputDir.getAbsolutePath());
            }
        }
    }
    
    /**
     * Get the largest file in a directory. If isRecursive is true,
     * any descendant directories will also be checked for larger files.
     * @param dir Directory in which to search for largest file
     * @param isRecursive If true, all the descendant directories in dir will
     * be checked for larger files. If false, only the files in dir will be
     * checked.
     * @return Largest file or null if not found. 
     */
    public static File getLargestFile(File dir, boolean isRecursive) {
        List<File> largestFiles = new ArrayList<File>();
        largestFiles.addAll(Arrays.asList(dir.listFiles((FileFilter) FileFileFilter.FILE)));
        if (isRecursive) {
            File[] subDirs = dir.listFiles((FileFilter) DirectoryFileFilter.DIRECTORY);
            for(File subDir : subDirs) {
                File tmpLargest;
                if ((tmpLargest = getLargestFile(subDir, true)) != null) {
                    largestFiles.add(tmpLargest);
                }
            }
        }
        if (largestFiles.isEmpty()) {
            return null; // empty directory
        }
        long fileSize = 0;
        int index = 0;
        for (int i = 0; i < largestFiles.size(); i++) {
            if (largestFiles.get(i).length() > fileSize) {
                fileSize = largestFiles.get(i).length();
                index = i;
            }
        }
        return largestFiles.get(index);
    }
}
