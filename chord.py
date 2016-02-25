"""
Created by: Jake Jorgenson
Thursday, 2/25/2016, 4:00pm

I shall work on this some more in the coming days.
Will begin work at the "root_as_majmaj7chord" line,
which is basically going to be a repeat of porevious
definitions, which defined major and minor chords and
put them into the chord_dict dictionary. Once all of
the code defining all the common chords is there, I hope
to work out a means of implementing these chords into 
a measure object of some sort & bring in the notes of
each measure to find the possible chords.
"""

#Code below: Note value as the root of the chord
chord_dict = []

def root_of_majchord(note):
	#print("Note as root of major chord: ")
	chord_type = []
	root = note
	majthird = note + 4
	fifth = note + 7
	chord_type.extend((root,majthird,fifth))
	#print(chord_type)
	chord_dict.extend(("root_of_majchord",chord_type))

def root_of_minchord(note):
	#print("Note as root of minor chord: ")
	chord_type = []
	root = note
	minthird = note + 3
	fifth = note + 7
	chord_type.extend((root,minthird,fifth))
	#print(chord_type)
	chord_dict.extend(("root_of_minchord",chord_type))

def root_of_majmaj7chord(note):
	#print("Note as root of major major7 chord: ")
	chord_type = []
	root = note
	majthird = note + 4
	fifth = note + 7
	majseventh = note + 11
	chord_type.extend((root,majthird,fifth,majseventh))
	#print(chord_type)

def root_of_majmin7chord(note):
	#print("Note as root of major minor7 chord: ")
	chord_type = []
	root = note
	majthird = note + 4
	fifth = note + 7
	minseventh = note + 10
	chord_type.extend((root,majthird,fifth,minseventh))
	#print(chord_type)

def root_of_minmaj7chord(note):
	#print("Note as root of minor major7 chord: ")
	chord_type = []
	root = note
	minthird = note + 3
	fifth = note + 7
	majseventh = note + 11
	chord_type.extend((root,minthird,fifth,majseventh))
	#print(chord_type)

def root_of_minmin7chord(note):
	#print("Note as root of minor minor7 chord: ")
	chord_type = []
	root = note
	minthird = note + 3
	fifth = note + 7
	minseventh = note + 10
	chord_type.extend((root,minthird,fifth,minseventh))
	#print(chord_type)

"""
*
*
Code below: Note value as the major third of the chord
*
*
"""

def third_of_majchord(note):
	#print("Note as third of major chord: ")
	chord_type = []
	root = note - 4
	majthird = note
	fifth = note + 3
	chord_type.extend((root,majthird,fifth))
	#print(chord_type)

def third_of_majmaj7chord(note):
	#print("Note as third of major major7 chord: ")
	chord_type = []
	root = note - 4
	majthird = note
	fifth = note + 3
	majseventh = note + 7
	chord_type.extend((root,majthird,fifth,majseventh))
	#print(chord_type)

def third_of_majmin7chord(note):
	#print("Note as third of major minor7 chord: ")
	chord_type = []
	root = note - 4
	majthird = note
	fifth = note + 3
	minseventh = note + 6
	chord_type.extend((root,majthird,fifth,minseventh))
	#print(chord_type)

"""
*
*
Code below: Note value as the minor third of the chord
*
*
"""

def third_of_minchord(note):
	#print("Note as third of minor chord: ")
	chord_type = []
	root = note - 3
	minthird = note
	fifth = note + 4
	chord_type.extend((root,minthird,fifth))
	#print(chord_type)

def third_of_minmaj7chord(note):
	#print("Note as third of minor major7 chord: ")
	chord_type = []
	root = note - 3
	minthird = note
	fifth = note + 4
	majseventh = note + 8
	chord_type.extend((root,minthird,fifth,majseventh))
	#print(chord_type)

def third_of_minmin7chord(note):
	#print("Note as third of minor minor7 chord: ")
	chord_type = []
	root = note - 3
	minthird = note
	fifth = note + 4
	minseventh = note + 7
	chord_type.extend((root,minthird,fifth,minseventh))
	#print(chord_type)

#needs more for notes as the fifth or maj./min. 7th of a chord

def main():

	note = 12

	n = int(input("Enter a number between 0 and 88: "))

	#88 notes on a piano

	#est. chord options: root
	if 0 <= n <= 77: #88-11
		#print("Root of chords....")
		root_of_majchord(n)
		root_of_minchord(n)
		root_of_majmaj7chord(n)
		root_of_majmin7chord(n)
		root_of_minmaj7chord(n)
		root_of_minmin7chord(n)

	#est. chord options: maj. third
	if 4 <= n <= 81: #0+4, 88-7
		#print("Major third of chords....")
		third_of_majchord(n)
		third_of_majmaj7chord(n)
		third_of_majmin7chord(n)

	#est. chord options: min. third
	if 3 <= n <= 80: #0+3, 88-8
		#print("Minor third of chords....")
		third_of_minchord(n)
		third_of_minmaj7chord(n)
		third_of_minmin7chord(n)

	print(chord_dict)

	#scratchwork for assigning pitch names to these values
	"""
	if nval // 12 == 0:
		print("C")
	if nval // 12 == 1:
		print("Db")
	if nval // 12 == 2:
		print("D")
	if nval // 12 == 3:
		print("Eb")
	if nval // 12 == 4:
		print("E")
	if nval // 12 == 5:
		print("F")
	if nval // 12 == 6:
		print("Gb")
	if nval // 12 == 7:
		print("G")
	if nval // 12 == 8:
		print("Ab")
	if nval // 12 == 9:
		print("A")
	if nval // 12 == 10:
		print("Bb")
	if nval // 12 == 11:
		print("B")
	"""
main()