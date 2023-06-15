const pingServer = async () => {
    try {
        const response = await fetch('/ping');
        if (response.status === 200) {
            document.getElementById('status').innerHTML = 'Yes';
            document.querySelector('.dot').classList.remove('down');
            document.querySelector('.dot').classList.add('up');
        } else {
            document.getElementById('status').innerHTML = 'down';
            document.querySelector('.dot').classList.remove('up');
            document.querySelector('.dot').classList.add('down');
        }
    } catch (error) {
        document.getElementById('status').innerHTML = "down";
        document.querySelector('.dot').classList.remove('up');
        document.querySelector('.dot').classList.add('down');
    }
};

const getStatus = async () => {
    try {
        const response = await fetch('/status');
        const json = await response.json();

        const { ip, port, host } = json;

        return { ip, port, host };
    } catch (error) {
        return { ip: 'UNKNOWN', port: 'UNKNOWN', host: 'UNKNOWN' };
    }
};


const updateStatus = async () => {
    const { ip, port, host } = await getStatus();
    document.getElementById('ip').innerHTML = `${ip}`;
    document.getElementById('port').innerHTML = `${port}`;
    document.getElementById('host').innerHTML = `${host}`;
};

const getDMX = async () => {
    try {
        const response = await fetch('/dmx');
        const json = await response.json();

        const { universe, address, panel } = json;

        return { universe, address, panel };
    } catch (error) {
        return { universe: 'UNKNOWN', address: 'UNKNOWN', panel: 'UNKNOWN' };
    }
};

const updatedmx = async () => {
    const { universe, address, panel } = await getDMX();
    document.getElementById('universe').innerHTML = `${universe}`;
    document.getElementById('address').innerHTML = `${address}`;
    document.getElementById('panel').innerHTML = `${panel}`;
};

pingServer();
updateStatus();
updatedmx();

setInterval(pingServer, 1000);
setInterval(updateStatus, 5000);
setInterval(updatedmx, 5000);