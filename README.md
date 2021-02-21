#Notepad

### ## Java Notepad Application with Design Patterns

##### The application has these functions:
- File opening, closing, saving and creating.
- Finding words written in Notepad and replacing those found words.
- Writes the correct and incorrect words by comparing the words written in Notepad with the words.txt file in the project.
- If there is an error in the written words due to the substitution of two letters, these words are found during the correction process and the correct version is printed on the screen and replaced with the correct version.
- The theme can be changed while using the application from the theme menu.
- The last letters written can be undone independently of the cursor by pressing the undo button.

##### Installation
- Clone the repo:
`git@github.com:eymiramazan/notepad.git`<br>
- Open with Intellij.

##### Design Patterns
- Abstract factory, iterator, singleton and command patterns are used in this project.
1.  Abstract factory pattern for theme,
2.  Command pattern for undo feature,
3.  Iterator for Hashset traverse,
4.  Singleton for notepad and file operations class, which should have a single object.
