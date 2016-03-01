"""
Created by: Jake Jorgenson
Thursday, 3/1/2016, 4:00pm

This is a revised/condensed version of my chord.py
file which I began working on late last week. All
of the major types of chords are now covered, from
root-position chords to first and second inversion
chords as well.

The next thing to be worked on in a future update
would be to make only the chords whose notes fall
in the 88-note range print out.
"""

chord_dict = {}

note = int(input("Enter a number between 0 and 88: "))

def chord(note):
	"""
	Some basic notes in a chord below this comment:
	-root is the root of a chord
	-majthird is the third of a major chord
	-minthird is the third of a minor chord
	-fifth is the fifth of a major/minor chord
	-majseventh is the major seventh of a major/minor chord
	-minseventh is the minor seventh of a major/minor chord
	"""
	root = note
	majthird = note + 4
	minthird = note + 3
	fifth = note + 7
	majseventh = note + 11
	minseventh = note + 10

	"""
	In music theory, 6/5 signifies a 'first inversion chord,'
	or one with the desired note as the third of a chord.
	(I'll just add a 6 to the end of stuff for simplicity-sake)

	Thus, I added a '_M6' to the end of major chords in first
	inversion, and '_m6' to the end of minor chords in first
	inversion.
	"""
	root_M6 = note - 4
	majthird_M6 = note
	fifth_M6 = note + 3
	majseventh_M6 = note + 7
	minseventh_M6 = note + 6

	root_m6 = note - 3
	minthird_m6 = note
	fifth_m6 = note + 4
	majseventh_m6 = note + 8
	minseventh_m6 = note + 7

	"""
	In music theory, 4/3 signifies a 'second inversion chord,'
	or one with the desired note on the bottom of a chord as 
	the fifth of a chord. (I'll just add a 4 to the end of 
	stuff for simplicity-sake)

	Thus, I added a '_M4' to the end of major chords in second
	inversion, and '_m4' to the end of minor chords in first
	inversion.
	"""
	root_M4 = note - 7
	majthird_M4 = note - 3
	fifth_M4 = note
	majseventh_M4 = note + 4
	minseventh_M4 = note + 3

	root_m4 = root_M4
	minthird_m4 = note - 4
	fifth_m4 = note
	majseventh_m4 = majseventh_M4
	minseventh_m4 = minseventh_M4

	"""
	************************************************************
	||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
	************************************************************
	And now, to build a dictionary with all the chords inside
	using chord_dict. The code below builds individual lists
	to contain each element of a chord, then the list object
	will be inserted into the dictionary with differing keys.
	************************************************************
	||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
	************************************************************
	"""

	"""
	*********************************************
	*********************************************
	Below: given note as the root of the chord

	Will use MM7/Mm7/mM7/mm7 for root-position 
	7 chords. Will also use "7" when putting 
	them in the dictionary.
	*********************************************
	*********************************************
	"""
	chord_list_Mtriad = []
	chord_list_Mtriad.extend((root,majthird,fifth))
	chord_dict["Major Triad"] = chord_list_Mtriad

	chord_list_mtriad = []
	chord_list_mtriad.extend((root,minthird,fifth))
	chord_dict["Minor Triad"] = chord_list_mtriad

	chord_list_MM7 = []
	chord_list_MM7.extend((root,majthird,fifth,majseventh))
	chord_dict["Major Major 7 Chord"] = chord_list_MM7

	chord_list_Mm7 = []
	chord_list_Mm7.extend((root,majthird,fifth,minseventh))
	chord_dict["Major Minor 7 Chord"] = chord_list_Mm7

	chord_list_mM7 = []
	chord_list_mM7.extend((root,minthird,fifth,majseventh))
	chord_dict["Minor Major 7 Chord"] = chord_list_mM7

	chord_list_mm7 = []
	chord_list_mm7.extend((root,minthird,fifth,minseventh))
	chord_dict["Minor Minor 7 Chord"] = chord_list_mm7

	"""
	*********************************************
	*********************************************
	Below: given note as the third of the chord

	Will use MM6/Mm6/mM6/mm6 for first inversion 
	7 chords. Will also use "6/5" when putting 
	them in the dictionary.
	*********************************************
	*********************************************
	"""
	chord_list_Mtriad6 = []
	chord_list_Mtriad6.extend((root_M6,majthird_M6,fifth_M6))
	chord_dict["Major Triad 6"] = chord_list_Mtriad6

	chord_list_mtriad6 = []
	chord_list_mtriad6.extend((root_m6,minthird_m6,fifth_m6))
	chord_dict["Minor Triad 6"] = chord_list_mtriad6
	
	chord_list_MM6 = []
	chord_list_MM6.extend((root_M6,majthird_M6,fifth_M6,majseventh_M6))
	chord_dict["Major Major 6/5 Chord"] = chord_list_MM6

	chord_list_Mm6 = []
	chord_list_Mm6.extend((root_M6,majthird_M6,fifth_M6,minseventh_m6))
	chord_dict["Major Minor 6/5 Chord"] = chord_list_Mm6

	chord_list_mM6 = []
	chord_list_mM6.extend((root_m6,minthird_m6,fifth_m6,majseventh_m6))
	chord_dict["Minor Major 6/5 Chord"] = chord_list_mM6

	chord_list_mm6 = []
	chord_list_mm6.extend((root_m6,minthird_m6,fifth_m6,minseventh_m6))
	chord_dict["Minor Minor 6/5 Chord"] = chord_list_mm6
	
	"""
	*********************************************
	*********************************************
	Below: given note as the fifth of the chord

	Will use MM4/Mm4/mM4/mm4 for second inversion 
	7 chords. Will also use "4/3" when putting 
	them in the dictionary.
	*********************************************
	*********************************************
	"""
	chord_list_Mtriad4 = []
	chord_list_Mtriad4.extend((root_M4,majthird_M4,fifth_M4))
	chord_dict["Major Triad 4"] = chord_list_Mtriad4

	chord_list_mtriad4 = []
	chord_list_mtriad4.extend((root_m4,minthird_m4,fifth_m4))
	chord_dict["Minor Triad 4"] = chord_list_mtriad4
	
	chord_list_MM4 = []
	chord_list_MM4.extend((root_M4,majthird_M4,fifth_M4,majseventh_M4))
	chord_dict["Major Major 4/3 Chord"] = chord_list_MM4

	chord_list_Mm4 = []
	chord_list_Mm4.extend((root_M4,majthird_M4,fifth_M4,minseventh_m4))
	chord_dict["Major Minor 4/3 Chord"] = chord_list_Mm4

	chord_list_mM4 = []
	chord_list_mM4.extend((root_m4,minthird_m4,fifth_m4,majseventh_m4))
	chord_dict["Minor Major 4/3 Chord"] = chord_list_mM4

	chord_list_mm4 = []
	chord_list_mm4.extend((root_m4,minthird_m4,fifth_m4,minseventh_m4))
	chord_dict["Minor Minor 4/3 Chord"] = chord_list_mm4

	"""
	*********************************************
	*********************************************
	To Be Continued: root as the fifth of a chord

	Will use MM2/Mm2/mM2/mm2 for third inversion 
	7 chords. Will also use "2" when putting 
	them in the dictionary.

	(Would be a repeat of the other chord types,
	just alter some variables around)
	*********************************************
	*********************************************
	"""

def main():
	
	chord(note)

	if 0 <= note <= 88:
		"""
		print("Major Triad: " + str(chord_dict["Major Triad"]))
		print("Minor Triad: " + str(chord_dict["Minor Triad"]))
		print("Major Minor 7 Chord: " + str(chord_dict["Major Minor 7 Chord"]))
		print("Major Major 7 Chord: " + str(chord_dict["Major Major 7 Chord"]))
		"""

	else:
		print("Error! Value not in range!")

main()