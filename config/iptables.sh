#!/bin/bash

# 自己机器对外开放的端口
Open_ports="80 25 110 10 3306 6379"
# internet的数据可以进入自己机器的端口
Allow_ports="53 80 20 21"

#init

iptables -F
iptables -X
iptables -t nat -F
iptables -t nat -X
iptables -P INPUT DROP

for open in $Open_ports ; do
echo $open
iptables -A INPUT -i eth0 -p tcp --dport $open -j ACCEPT
iptables -A INPUT -i eth0 -p udp --dport $open -j ACCEPT
done

for allow in $Allow_ports ; do
echo $allow
iptables -A INPUT -i eth0 -p tcp --sport $allow -j ACCEPT
iptables -A INPUT -i eth0 -p udp --sport $allow -j ACCEPT
done

# This is the last ruler , it can make you firewall better
#iptables -A INPUT -i eth0 -p tcp -j REJECT --reject-with tcp-reset
#iptables -A INPUT -i eth0 -p udp -j REJECT --reject-with icmp-port-unreachable