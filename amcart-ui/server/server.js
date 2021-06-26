const express = require('express')
const PORT = process.env.PORT || 3000;

const app = express()
app.use(express.static('public'))

app.get('/*', function(req, res) { 
  res.sendFile(__dirname + '/public/index.html')
});

app.listen(PORT, () => console.log('UI service listening on port '+PORT+'!'))
