# Enigma Machine
Implementation of the WWII Enigma Machine.

See <https://en.wikipedia.org/wiki/Enigma_machine> 
for general information on the machines.

A good explanation is located at 
<http://enigma.louisedade.co.uk/howitworks.html>
though I should note that the "static wheel" mentioned does not exist in this
program as it is not required (and has no part in the cryptographic process).

## Requirements

This program has been tested with Java 8, but should work with Java 6+.
If you want to run the test suite, they were written with JUnit 4, but JUnit 3+,
with the latest version of Hamcrest for the Matchers in the Test Suite.

## Usage
###Introduction

This program is very simple. It may be called with a plugboard
and 0 or more rotor configurations on the command line, in the
order of the 1st rotor, the 2nd rotor and so on, and finally,
the plugboard configuration (which can be an empty file to
indicate that the plugboard has no wires attached).

###Syntax
####Rotor Files

The Rotor file is on one line and lists the mapping of inputs to
outputs with the first number in the file representing an input on
'A', and the number in it indicating the output. For example, 0
corresponds to the letter 'A', 1 to the letter 'B', and 25 to the letter 'Z'. 
For example:
>`1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 0`

represents a mapping up one place in the alphabet. With the rotor in
the initial position, an 'A' will be mapped to 'B', a 'B' to 'C',
and 'Z' to 'A'.
Remember, this mapping will not hold after the first translation as
the rotor will advance one position forward, with 'A' mapping to 'C'
and so on.
Finally, the rotor file must contain exactly one each of the numbers 0-25, 
separated by a single space each. Any other
combination will result in a syntax error and the program will abort

####Plugboard Files

The syntax of the plugboard file is similar, but lists pairs of
numbers. These numbers again represent letters of the Alphabet, and
represent which pairs of letters will be joined together on the
virtual plugboard of the enigma machine in this program. Duplicates,
an odd number of numbers, incorrect spacing or numbers in the wrong
range (0-25) will result in a syntax error or unexpected behaviour. For example:
`25 8`
will swap the letters 'I' and 'Z' in the plugboard (which sits between the input
and the first rotor, and then the first rotor and the output); `25 10 22 9 21 4`
will swap the letters 'Z' with 'K', 'J' with 'W' and 'V' with 'E', with all 
other letters remaining the same.


An empty plugboard file will yield no swaps and effectively renders the 
plugboard inert with the identity mapping of the letters of the alphabet.

###Usage

Using the program is quite simple. Enter the message you want to
translate through your chosen configuration of rotors and plugboard
and press ENTER. The result will then be shown below. You may enter
further messages in the same way.
All messages must contain only the capital letters A-Z, and spaces. If a 
message contains any other characters, they will be replaced
by a dot (.) in the result. Note that invalid characters do not
cause the rotors to change positions, as no translation has taken
place.
Enter 'h' at any time to show this help message again,
or enter 'q' to exit the program.

Remember, you can always decrypt your messages by entering the
encrypted version into an *identically* configured enigma machine.
Remember also that the configuration of the machine includes the
position of the rotors, which will change with every valid character
entered.

## Building
Build everything under `/src` for the base program. You may also wish to compile
and run the unit `testsuite`, which is under /test.

## Copyright

### Rotor and Plugboard configuration files
The .rot and .pb files in the rotors/ and plugboards/ directories
are taken from an Imperial College exercise (www.imperial.ac.uk/computing).

They are not written by me, but they are not a part of the program
(and could/should be written by any user of the program). They are not in the
binary when it is built and are provided because they are useful with the 
program.

### Everything else
I wrote everything else, it's MIT Licensed, which you can find in the LICENSE
file.

## About
This is based on an exercise taken at the beginning of Year 2 by Imperial
College Computing Students - to write an object-oriented Enigma Machine 
using C++. 

This implements the same exercise, but in Java.