
const WebSocket = require('ws');

const wss = new WebSocket.Server({ port: 3000 });

wss.on('connection', ws =>{
    console.log('[Server] A client was connected.');

    ws.on('close', () => { console.log('[Server] Client disconnected') });

    //broadcast to all
    ws.on('message', (data) => {
        console.log('[Server] Received message: ', data.toString());
        //broadcast to all clients
        wss.clients.forEach(client => client.send(data.toString()));
    });
})

