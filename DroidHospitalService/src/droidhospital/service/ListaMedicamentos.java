package droidhospital.service;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.feevale.droidhospital.db.DadosId;
import br.feevale.droidhospital.db.Medicamento;
import droidhospital.util.Conexao;
import droidhospital.util.Query;

public class ListaMedicamentos extends Transacao {

	private ArrayList<Medicamento> medicamentos;
	private DadosId dados;

	@Override
	public void setDadosRecebidos(Serializable dadosRecebidos) {
		dados = (DadosId) dadosRecebidos;

	}

	@Override
	public void executaTransacao() {
		medicamentos = new ArrayList<Medicamento>();

		try {

			StringBuilder sbQuery = new StringBuilder();

			String nome_medicamento = dados.getId();

			String sql = "select idmedicamento id, " 
					+ "       farmaco principio, "
					+ "       detentor laboratorio, "
					+ "       medicamento_referencia fantasia, "
					+ "       concentracao concentracao, "
					+ "       forma_farmaceutica forma "
					+ "from medicamentos m ";

			if (nome_medicamento.length() > 0) {
				sql += "where m.farmaco like '%" + nome_medicamento + "%' "
						+ "or m.medicamento_referencia like '%"
						+ nome_medicamento + "%' ";
			}
			sbQuery.append(sql);

			ResultSet resultSet = null;
			Conexao cnx = new Conexao();

			System.out.println(sbQuery.toString());
			try {

				Query q = new Query(cnx);

				q.setSQL(sbQuery);

				resultSet = q.executeQuery();

				while (resultSet.next()) {

					Medicamento medicamento = new Medicamento();

					medicamento.setIdMedicamento(resultSet.getInt("id"));
					medicamento.setFarmaco(resultSet.getString("principio"));
					medicamento.setDetentor(resultSet.getString("laboratorio"));
					medicamento.setMedicamentoReferencia(resultSet
							.getString("fantasia"));
					medicamento.setConcentracao(resultSet
							.getString("concentracao"));
					medicamento.setFormaFarmaceutica(resultSet
							.getString("forma"));

					medicamentos.add(medicamento);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				cnx.close();
				resultSet.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Serializable getDadosResposta() {
		// TODO Auto-generated method stub
		return medicamentos;
	}

}
