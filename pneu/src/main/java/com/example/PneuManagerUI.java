package com.example;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class PneuManagerUI extends JFrame {
    private JTextField campoCodPneu;
    private JTextArea areaResultado;
    private JButton botaoConsultar, botaoEstoque, r;

    public PneuManagerUI() {
        setTitle("Consulta de Pneu");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("Código do Pneu:"));
        campoCodPneu = new JTextField(10);
        add(campoCodPneu);

        botaoConsultar = new JButton("Consultar");
        add(botaoConsultar);

        // Só habilita depois da consulta para evitar erros
        botaoEstoque = new JButton("Mover para Estoque");
        botaoEstoque.setEnabled(false); 
        add(botaoEstoque);
  

        areaResultado = new JTextArea(15, 40);
        areaResultado.setEditable(false);
        add(new JScrollPane(areaResultado));

        botaoConsultar.addActionListener(e -> consultarPneu());
        botaoEstoque.addActionListener(e -> moverParaEstoque());
    }

    private void consultarPneu() {
        String cod = campoCodPneu.getText().trim();
        if (cod.isEmpty())
            return;

        try {
            System.out.println("Tentando conectar...");
            Connection con = Conexao.conectar();
            System.out.println("Conectado!");

            String sql = "SELECT 'ANTES' AS status, codpne, situac, DATBAI, MOTBAI, DATRET, SELECI FROM rodpne WHERE codpne = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cod);
            ResultSet rs = ps.executeQuery();

            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("Status: ").append(rs.getString("status")).append("\n");
                sb.append("Código: ").append(rs.getString("codpne")).append("\n");
                sb.append("Situação: ").append(rs.getString("situac")).append("\n");
                sb.append("Data Baixa: ").append(rs.getString("DATBAI")).append("\n");
                sb.append("Motivo Baixa: ").append(rs.getString("MOTBAI")).append("\n");
                sb.append("Data Retorno: ").append(rs.getString("DATRET")).append("\n");
                sb.append("Seleci: ").append(rs.getString("SELECI")).append("\n\n");
            }

            areaResultado.setText(sb.toString());
            botaoEstoque.setEnabled(true);
            con.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            areaResultado.setText("Erro na consulta: " + ex.getMessage());
        }
    }

    private void moverParaEstoque() {
        String cod = campoCodPneu.getText().trim();

        try (Connection con = Conexao.conectar()) {
            con.setAutoCommit(false);

            // 1. Buscar ID do histórico
            PreparedStatement selHist = con.prepareStatement(
                    "SELECT id_hpn FROM rodhpn WHERE codpne = ? AND tiphis = 'ba'");
            selHist.setString(1, cod);
            ResultSet rs = selHist.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_hpn");

                PreparedStatement del = con.prepareStatement(
                        "DELETE FROM rodhpn WHERE id_hpn = ?");
                del.setInt(1, id);
                del.executeUpdate();
            }

            // 2. Atualiza status do pneu
            PreparedStatement upd = con.prepareStatement(
                    "UPDATE rodpne SET situac = 'ES', DATBAI = NULL, MOTBAI = NULL, DATRET = NULL, SELECI = NULL WHERE codpne = ?");
            upd.setString(1, cod);
            upd.executeUpdate();

            con.commit();

            // 3. Consulta novamente
            PreparedStatement ps = con.prepareStatement(
                    "SELECT 'DEPOIS' AS status, codpne, situac, DATBAI, MOTBAI, DATRET, SELECI FROM rodpne WHERE codpne = ?");
            ps.setString(1, cod);
            ResultSet rs2 = ps.executeQuery();

            StringBuilder sb = new StringBuilder();
            while (rs2.next()) {
                sb.append("Status: ").append(rs2.getString("status")).append("\n");
                sb.append("Código: ").append(rs2.getString("codpne")).append("\n");
                sb.append("Situação: ").append(rs2.getString("situac")).append("\n");
                sb.append("Data Baixa: ").append(rs2.getString("DATBAI")).append("\n");
                sb.append("Motivo Baixa: ").append(rs2.getString("MOTBAI")).append("\n");
                sb.append("Data Retorno: ").append(rs2.getString("DATRET")).append("\n");
                sb.append("Seleci: ").append(rs2.getString("SELECI")).append("\n\n");
            }

            areaResultado.setText(sb.toString());
            botaoEstoque.setEnabled(false);

        } catch (SQLException ex) {
            areaResultado.setText("Erro ao atualizar: " + ex.getMessage());
        }
    }
}
