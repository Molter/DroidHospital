package droidhospital.service;

import java.io.Serializable;
import java.sql.ResultSet;

import br.feevale.droidhospital.db.Medicamento;
import br.feevale.droidhospital.db.MedicamentoDescription;
import droidhospital.util.Conexao;
import droidhospital.util.Query;

public class BuscaMedicamento extends Transacao {

	private MedicamentoDescription dados;
	Medicamento medicamento;

	@Override
	public void setDadosRecebidos(Serializable dadosRecebidos) {
		dados = (MedicamentoDescription) dadosRecebidos;

	}

	@Override
	public void executaTransacao() {

		try {

			StringBuilder sbQuery = new StringBuilder();

			String id_medicamento = dados.getBusca_medicamento();

			String sql = " select idmedicamento id, "
					+ "       farmaco principio, "
					+ "       detentor laboratorio, "
					+ "       medicamento_referencia fantasia, "
					+ "       concentracao concentracao, "
					+ "       forma_farmaceutica forma "
					+ "    from medicamentos m "
					+ "    where m.idmedicamento = " + id_medicamento;

			sbQuery.append(sql);

			ResultSet resultSet = null;
			Conexao cnx = new Conexao();

			System.out.println(sbQuery.toString());
			try {

				Query q = new Query(cnx);

				q.setSQL(sbQuery);

				resultSet = q.executeQuery();

				medicamento = new Medicamento();
				
				resultSet.first();

				medicamento.setIdMedicamento(resultSet.getInt("id"));
				medicamento.setPrincipio(resultSet.getString("principio"));
				medicamento.setLaboratorio(resultSet.getString("laboratorio"));
				medicamento.setFantasia(resultSet.getString("fantasia"));
				medicamento
						.setConcentracao(resultSet.getString("concentracao"));
				medicamento.setFormaFarmaceutica(resultSet.getString("forma"));

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
		return medicamento;
	}

}
