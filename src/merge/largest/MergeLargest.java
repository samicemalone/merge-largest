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
package merge.largest;

/**
 *
 * @author Sam Malone
 */
public class MergeLargest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Arguments arg;
        try {
            arg = Arguments.parse(args);
            if(arg == null) {
                printHelp();
                System.exit(0);
            }
            Arguments.validate(arg);
        } catch(Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
    
    public static void printHelp() {
        System.out.println("Usage: MergeLargest [-hrt] [-d DESTINATION] DIRECTORY...");
        System.out.println("The largest file in each DIRECTORY will be merged into DESTINATION if -d is set.");
        System.out.println("Otherwise they will be merged into the parent directory of the first DIRECTORY");
        System.out.println("argument. DIRECTORY is then deleted.");
        System.out.println("After moving the largest file out of each DIRECTORY, DIRECTORY is deleted.");
        System.out.println("The only way DIRECTORY will not be deleted is if the recursive option (-r)");
        System.out.println("isn't set, AND DIRECTORY contains no files (regardless of subdirs).");
        System.out.println();
        System.out.println("    -d, --dest-dir DESTINATION  Destination directory for the largest file");
        System.out.println("                                in each DIRECTORY to be moved to");
        System.out.println("    -h, --help                  Displays this message and exits");
        System.out.println("    -r, --recursive             Finds the largest file in each DIRECTORY or");
        System.out.println("                                its subdirectories. Each DIRECTORY will still");
        System.out.println("                                be removed.");
        System.out.println("    -t, --test                  Test mode will execute and display output,");
        System.out.println("                                but no files will be moved/deleted.");
    }
}
