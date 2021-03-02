package br.com.southsystem.utils;

public class DatabaseConstants {

	public static final String UK_CPF = "UK_CPF";

	public static synchronized String recoveryCreateFriendlyMessage(final String cause) {
		if (cause.contains(UK_CPF))
			return "Usuário com esse CPF já existe na base de dados";
		return "Erro ao criar recurso. Favor atualizar a página e tentar novamente mais tarde.";
	}
}
