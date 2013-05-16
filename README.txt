NAME
   merge-largest - Moves the largest file out of each input directory, to a
                   destination, and then deletes the original input directory.

SYNOPSIS
   merge-largest [-hrt] [-d DESTINATION] DIRECTORY...

DESCRIPTION
   The largest file in each DIRECTORY will be merged into DESTINATION if -d is set.
   If -d is not set, DESTINATION will default to the current working directory.
   After moving the largest file out of each DIRECTORY, DIRECTORY is deleted.
   
   If the recursive flag is set, and there are no files in any descendant
   directories within DIRECTORY, then DIRECTORY will not be deleted. If the
   recursive flag is not set and DIRECTORY doesn't contain any files, it will not
   be deleted.

OPTIONS

   DIRECTORY...
      The DIRECTORY argument(s) are the input directories to be searched for a
      largest file. DIRECTORY will be deleted if a largest file is moved.
      
   -d DESTINATION, --dest-dir DESTINATION
      This is the directory that the largest file will be moved to. If this
      option is not set, the current working directory will be used as a 
      default.

   -h, --help
      The help message will be output and the program will exit.
      
   -t, --test
      This flag makes merge-largest do a dry-run through the program,
      outputting the file names of the files that would be moved and
      directories that will be deleted. No files will actually be moved/deleted.

COPYRIGHT
   Copyright (c) 2013, Sam Malone. All rights reserved.

LICENSING
   The merge-largest source code, binaries and documentation are licensed under a BSD
   License. See LICENSE for details.

AUTHOR
   Sam Malone