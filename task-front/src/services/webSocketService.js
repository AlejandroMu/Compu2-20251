const useWebSocket = (callback) => {
    const ws = new WebSocket("ws://localhost:9090/task-manager/ws-connect?token=sadsad");
    
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
