package org.unidal.net;

import java.net.InetSocketAddress;
import java.util.List;

public interface SocketAddressProvider {
    List<InetSocketAddress> getAddresses();
}