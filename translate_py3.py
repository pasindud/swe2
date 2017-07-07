
import json
import urllib
import codecs
import urllib.parse
import urllib.request

KEY = ""
def translate(api_key, text, sourcelang, targetlang):
		text = urllib.parse.quote(text.encode('utf-8'))

		response = urllib.request.urlopen("https://translation.googleapis.com/language/translate/v2?key="+KEY+"&q="+text+"&source="+sourcelang+"&target="+targetlang+"")
		html = response.read()
		# print html
		data = json.loads(html)
		return data['data']['translations'][0]['translatedText']

TRANSLATE_MAIN_FILE="UI/areas/common/localization/en-us.json"

a =  json.load(open(TRANSLATE_MAIN_FILE))

TARGET_CODE = "si"
OUTPUT_DIC = {}
for workkey in a[0]:
	text = a[0][workkey]
	if not isinstance(text, list):
		translated_text =  translate(KEY, text , 'en', TARGET_CODE)
		OUTPUT_DIC.update({workkey: translated_text})
	else:
		OUTPUT_DIC.update({workkey:text})

OUT = [OUTPUT_DIC]

with open('UI/areas/common/localization/automated-'+TARGET_CODE+'.txt', 'wb') as outfile:
    json.dump(OUT, codecs.getwriter('utf-8')(outfile),  ensure_ascii=False)
