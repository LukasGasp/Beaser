var dmxaddress;
var panelamount;

// Buttons

function submit(path, address) {
    fetch(path, {
        method: 'POST',
        body: address
    })
        .then(alert("Data was sent successfully but method not implemented yet"))
}

window.onload = function () {
    var button_address = document.getElementById('button_address');
    button_address.onclick = function () {
        var address = prompt('Enter new DMX Address:', dmxaddress);
        if(address){
            submit('/setaddress', address);
        } else {
            alert("Abort")
        }
    }

    var button_panel = document.getElementById('button_panel');
    button_panel.onclick = function () {
        var panel = prompt('Enter new Panel amount:', panelamount);
        if(panel){
            submit('/setpanel', panel);
        } else {
            alert("Abort")
        }
    }
}

// Data fetching

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

        const { universe, address, range, panel } = json;

        return { universe, address, range, panel };
    } catch (error) {
        return { universe: 'UNKNOWN', address: 'UNKNOWN', panel: 'UNKNOWN' };
    }
};

const updatedmx = async () => {
    const { universe, address, range, panel } = await getDMX();
    dmxaddress = address;
    panelamount = panel;
    document.getElementById('universe').innerHTML = `${universe}`;
    document.getElementById('address').innerHTML = `${address} - ${range}`;
    document.getElementById('panel').innerHTML = `${panel}`;
};

pingServer();
updateStatus();
updatedmx();

setInterval(pingServer, 1000);
setInterval(updateStatus, 5000);
setInterval(updatedmx, 5000);