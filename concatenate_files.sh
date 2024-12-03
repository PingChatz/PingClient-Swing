#!/bin/bash

# Define the target directory
target_directory="/Users/ali/Documents/Projects/207.nosync/PingClient-Swing/src"

# Define the output file
output_file="all_code.txt"

# Clear the output file if it exists
> "$output_file"

# Traverse .java and .xml files in the target directory and its subdirectories
find "$target_directory" -type f \( -name "*.java" -o -name "*.xml" \) | while read -r file; do
    # Append the file name as a header
    echo "===== $file =====" >> "$output_file"
    # Append the file contents
    cat "$file" >> "$output_file"
    # Add a newline for separation
    echo -e "\n" >> "$output_file"
done
