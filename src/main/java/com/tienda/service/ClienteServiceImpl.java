
package com.tienda.service;

import com.tienda.dao.ClienteDao;
import com.tienda.dao.CreditoDao;
import com.tienda.model.Cliente;
import com.tienda.model.Credito;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//siguiente anotacion para que se pueda utilizar dentro de un controller 

@Service
public class ClienteServiceImpl implements ClienteService {

        @Autowired
    private ClienteDao clienteDao;
        
      @Autowired
    private CreditoDao creditoDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Cliente> getClientes() {
       return (List<Cliente>)clienteDao.findAll();
    }

    @Override
   @Transactional
    public void save(Cliente cliente) {
        Credito credito = cliente.getCredito(); //obtengo el objeto credito del cliente a salvar...
        credito = creditoDao.save(credito); //salvo el objeto credito en la tabla credito... se reasigna idcredito
        cliente.setCredito(credito);
       clienteDao.save(cliente);
    }

    @Override
    @Transactional
    public void delete(Cliente cliente) {
        clienteDao.delete(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente getCliente(Cliente cliente) {
        return clienteDao.findById(cliente.getIdCliente()).orElse(null);
    }
    
}
