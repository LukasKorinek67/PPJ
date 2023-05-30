

class TimestampHandler {
    timestampToDate(timestamp) {
        const date = new Date(timestamp*1000);
        const formattedDate = date.toLocaleString();
        return formattedDate;
    }
}
const timestampHandler = new TimestampHandler();
export default timestampHandler;

//export default new TimestampHandler();