package com.mcss.mcom.eth;

import com.mcss.mcom.Communicator;
import com.mcss.mcom.action.ReaderAction;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EthernetCommunicator implements Communicator, Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(EthernetCommunicator.class);

    private Socket socket;
    private OutputStream output;
    private PrintWriter writer;
    private InputStream input;
    private BufferedReader reader;
    private final ReaderAction action;
    private boolean conected;
    private final String host;
    private final Integer port;
    private Thread thread;
    private final int timeout;

    public EthernetCommunicator(String host, Integer port, ReaderAction action, Integer timeout) {
        this.host = host;
        this.port = port;
        this.action = action;
        this.timeout = timeout;
    }

    @Override
    public void connect() throws IOException {
        LOGGER.info("Conectando a: " + this.host + " puerto: " + this.port);
        this.socket = new Socket();
        this.socket.connect(new InetSocketAddress(host, port), this.timeout);
        this.output = socket.getOutputStream();
        this.writer = new PrintWriter(this.output, true);
        this.input = socket.getInputStream();
        this.reader = new BufferedReader(new InputStreamReader(input));
        this.conected = Boolean.TRUE;
        LOGGER.info("Conectado a: " + this.host + " puerto: " + this.port);
        this.listen();
    }

    @Override
    public void disconnect() throws IOException {
        this.socket.close();
        this.conected = false;
    }

    @Override
    public void write(String message) {
        this.writer.println(message);
    }

    @Override
    public void write(byte[] stream) throws IOException {
        output.write(stream);
    }

    @Override
    public ReaderAction getAction() {
        return action;
    }

    @Override
    public boolean isConnected() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void listen() throws IOException {
        LOGGER.info("Iniciando lectura");
        this.thread = new Thread(this, "Indicator Reader");
        this.thread.start();
    }

    @Override
    public void run() {
        while (this.conected) {
            try {
                this.action.doTask(this.reader.readLine());
            } catch (IOException ex) {
                try {
                    LOGGER.error("Error al procesar la lectura", ex);
                    this.disconnect();
                    this.connect();
                } catch (IOException ex1) {
                    LOGGER.error("Error al procesar la lectura", ex);
                }
            }
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.host);
        hash = 11 * hash + Objects.hashCode(this.port);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EthernetCommunicator other = (EthernetCommunicator) obj;
        if (!Objects.equals(this.host, other.host)) {
            return false;
        }
        if (!Objects.equals(this.port, other.port)) {
            return false;
        }
        return true;
    }

}
