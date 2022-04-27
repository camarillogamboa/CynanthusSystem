package edu.cynanthus.common.net;

import edu.cynanthus.common.Counter;
import edu.cynanthus.common.FunctionIterator;
import edu.cynanthus.common.SSV;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;

/**
 * El tipo Net.
 */
public final class Net {

    /**
     * Instancia un nuevo Net.
     */
    private Net() {
    }

    /**
     * Permite obtener mac.
     *
     * @return el mac
     */
    public static String getMac() {
        try {
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());

            byte[] mac = networkInterface.getHardwareAddress();
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < mac.length; i++)
                stringBuilder.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? ":" : ""));

            return stringBuilder.toString();
        } catch (SocketException | UnknownHostException e) {
            return "unknown";
        }
    }

    /**
     * Permite obtener ramdon mac.
     *
     * @return el ramdon mac
     */
    public static String getRamdonMac() {
        Random ramdon = new Random();
        return SSV.toSSVFormat(
            new FunctionIterator<>(
                new Counter(6),
                index -> {
                    String aDigit = Integer.toHexString(ramdon.nextInt(15));
                    String bDigit = Integer.toHexString(ramdon.nextInt(15));
                    return aDigit + bDigit;
                }
            ),
            ":"
        );
    }

}
