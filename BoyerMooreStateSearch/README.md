# Boyer-Moore State Search Application

## Overview
This Java application implements the Boyer-Moore algorithm using the bad character rule to search for patterns within the names of all 50 US states.

## Features
- **Display Text**: Shows all 50 US state names as a single concatenated string
- **Search**: Uses Boyer-Moore algorithm with bad character rule to find pattern matches
- **Menu-driven Interface**: Easy-to-use command-line interface with three options

## How to Run

### Prerequisites
- Java Development Kit (JDK) installed on your system
- Command line access

### Compilation
```bash
javac StateSearchApp.java
```

### Execution
```bash
java StateSearchApp
```

## Menu Options

### 1) Display the text
Shows the complete text containing all 50 US state names separated by spaces.

### 2) Search
Prompts you to enter a search pattern and finds all occurrences using the Boyer-Moore algorithm with bad character rule. The application will display:
- The indices where matches are found
- A preview of the text around each match

### 3) Exit program
Terminates the application.

## Example Usage

```
=== Boyer-Moore State Search Application ===
This application searches US state names using the Boyer-Moore algorithm (bad character rule)

Please select an option:
1) Display the text
2) Search
3) Exit program
Enter your choice (1-3): 2

Enter a pattern to search for: New

Searching for pattern: "New"
Using Boyer-Moore algorithm (bad character rule)...
Found 3 match(es) at indices:
  Index 196: "New Hampshire New Jersey New Mexico..."
  Index 210: "New Jersey New Mexico New York..."
  Index 221: "New Mexico New York North..."
```

## Algorithm Implementation

The application implements the **bad character rule** of the Boyer-Moore string searching algorithm:

1. **Bad Character Table**: Pre-processes the pattern to create a table of the rightmost occurrence of each character
2. **Pattern Matching**: Compares the pattern with the text from right to left
3. **Shift Calculation**: When a mismatch occurs, shifts the pattern based on the bad character rule to skip unnecessary comparisons

## Technical Details

- **Text Source**: All 50 US state names concatenated with spaces
- **Search Algorithm**: Boyer-Moore with bad character rule only
- **Time Complexity**: O(nm) worst case, O(n/m) best case (where n = text length, m = pattern length)
- **Space Complexity**: O(σ) where σ is the alphabet size (256 for ASCII)

## US States Included
Alabama, Alaska, Arizona, Arkansas, California, Colorado, Connecticut, Delaware, Florida, Georgia, Hawaii, Idaho, Illinois, Indiana, Iowa, Kansas, Kentucky, Louisiana, Maine, Maryland, Massachusetts, Michigan, Minnesota, Mississippi, Missouri, Montana, Nebraska, Nevada, New Hampshire, New Jersey, New Mexico, New York, North Carolina, North Dakota, Ohio, Oklahoma, Oregon, Pennsylvania, Rhode Island, South Carolina, South Dakota, Tennessee, Texas, Utah, Vermont, Virginia, Washington, West Virginia, Wisconsin, Wyoming

## Assignment Requirements Met

✅ Uses names of 50 US states as input text  
✅ Implements bad character rule of Boyer-Moore algorithm  
✅ Menu-driven interface with three options  
✅ Option 1: Display the text  
✅ Option 2: Search for patterns and display match indices  
✅ Option 3: Exit program  

## Author
Created as part of a Java programming assignment focusing on string searching algorithms.
