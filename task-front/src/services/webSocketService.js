const useWebSocket = (callback) => {
    const ws = new WebSocket("ws://localhost:8080/ws-connect");
    
    ws.onopen = () => {
      console.log("Conectado");
      ws.send("Hola a todos!");
    };
    
    ws.onmessage = (event) => {
      console.log("Mensaje recibido:", event.data);
      callback(event.data)
    };
    return ws;
}
export default useWebSocket;
