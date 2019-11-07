from PIL import Image,ImageTk

def toBinary(string):
	res = ""
	for i in string:
		res = res + bin(ord(i))[2:].zfill(8)
	return res

def code(img,text,name):
	rgb = img.convert('RGB')
	width = img.size[0]
	height = img.size[1]
	imgCode = Image.new("RGB",(width,height),"white")
	data = imgCode.load()
	msg = toBinary(text)
	count = 0
	for i in range(width):
		for j in range(height):
			r,g,b = rgb.getpixel((i,j))
			r_byte = "{0:b}".format(r)
			r_byte = list(r_byte)
			g_byte = "{0:b}".format(g)
			g_byte = list(g_byte)
			b_byte = "{0:b}".format(b)
			b_byte = list(b_byte)
			if(count < len(msg)):
				if(count < len(msg)):
					r_byte[len(r_byte) - 1] = msg[count]
					count += 1
				if(count < len(msg)):
					g_byte[len(g_byte) - 1] = msg[count]
					count += 1
				if(count < len(msg)):
					b_byte[len(b_byte) - 1] = msg[count]
					count += 1
			else:
				r_byte[len(r_byte) - 1] = "1"
				g_byte[len(g_byte) - 1] = "1"
				b_byte[len(b_byte) - 1] = "1"
			r = "".join(r_byte)
			r = int(r,2)
			g = "".join(g_byte)
			g = int(g,2)
			b = "".join(b_byte)
			b = int(b,2)
			data[i,j] = (r,g,b)
		imgCode.save(name + ".png", "PNG")

def decode(img):
	rgb = img.convert("RGB")
	width = img.size[0]
	height = img.size[1]
	msgCode = ""
	msg = ""
	for i in range(width):
		for j in range(height):
			r,g,b = rgb.getpixel((i,j))
			r_byte = "{0:b}".format(r)
			r_byte = list(r_byte)
			g_byte = "{0:b}".format(g)
			g_byte = list(g_byte)
			b_byte = "{0:b}".format(b)
			b_byte = list(b_byte)
			msgCode = msgCode + r_byte[len(r_byte) - 1] + g_byte[len(g_byte) - 1] + b_byte[len(b_byte) - 1]
	for i in range(0,len(msgCode),8):
		string = msgCode[i:i+8]
		if(string == "11111111"):
			break
		else:
			msg = msg + string
	msg = ''.join(chr(int(msg[i:i+8], 2)) for i in range(0, len(msg), 8))
	return msg