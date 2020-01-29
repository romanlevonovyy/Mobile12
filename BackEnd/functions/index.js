const functions = require('firebase-functions');

const json = [
    {
	"madename": "Sinoptik",
	"napryamok": "pryamo",
	"vologist":  "200",
	"dataservice": "20.20.2020",
	"datastart": "18.09.2019",
	"speedviter": "3 m/s",
	"atmotusk": "160 r. s.",
	"location": "Chernivtsi",
	"description": "shoasf",
    "temperaturel": "Temperature 18 C"
    },
       {
	"madename": "Sinoptik",
	"napryamok": "pryamo",
	"vologist":  "300",
	"dataservice": "20.20.2020",
	"datastart": "18.09.2019",
	"speedviter": "3 m/s",
	"atmotusk": "160 r. s.",
	"location": "Lviv",
	"description": "shota",
        "temperaturel": "Temperature 18 C"
    },
    {
	"madename": "Sinoptik",
	"napryamok": "pryamo",
	"vologist":  "300",
	"dataservice": "20.20.2020",
	"datastart": "18.09.2019",
	"speedviter": "3 m/s",
	"atmotusk": "160 r. s.",
	"location": "Kyiv",
	"description": "shota",
        "temperaturel": "Temperature 18 C"
    },
    {
	"madename": "Sinoptik",
	"napryamok": "pryamo",
	"vologist":  "490",
	"dataservice": "20.20.2020",
	"datastart": "18.09.2019",
	"speedviter": "3 m/s",
	"atmotusk": "160 r. s.",
	"location": "DOnetsk",
	"description": "shot",
        "temperaturel": "Temperature 18 C"
    },
    {
	"madename": "Sinoptik",
	"napryamok": "pryamo",
	"vologist":  "310",
	"dataservice": "20.20.2020",
	"datastart": "18.09.2019",
	"speedviter": "3 m/s",
	"atmotusk": "160 r. s.",
	"location": "Lviv",
	"description": "shota tam",
        "temperaturel": "Temperature 18 C"
    },
    {
	"madename": "Sinoptik",
	"napryamok": "pryamo",
	"vologist":  "321",
	"dataservice": "20.20.2020",
	"datastart": "18.09.2019",
	"speedviter": "3 m/s",
	"atmotusk": "160 r. s.",
	"location": "Luhansk",
	"description": "shot",
        "temperaturel": "Temperature 18 C"
    },
    {
	"madename": "Sinoptik",
	"napryamok": "pryamo",
	"vologist":  "212",
	"dataservice": "20.20.2020",
	"datastart": "18.09.2019",
	"speedviter": "3 m/s",
	"atmotusk": "160 r. s.",
	"location": "Novoyavorivsk",
	"description": "shota",
        "temperaturel": "Temperature 18 C"
    }
];
exports.equipment = functions.https.onRequest((request, response) => {response.send(json);
});