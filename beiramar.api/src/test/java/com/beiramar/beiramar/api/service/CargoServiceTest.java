package com.beiramar.beiramar.api.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.beiramar.beiramar.api.infrastructure.features.dto.cargoDtos.CargoAtualizacaoDto;
import com.beiramar.beiramar.api.infrastructure.features.dto.cargoDtos.CargoCadastroDto;
import com.beiramar.beiramar.api.infrastructure.features.dto.cargoDtos.CargoListagemDto;
import com.beiramar.beiramar.api.infrastructure.features.dto.mapper.CargoMapper;
import com.beiramar.beiramar.api.infrastructure.persistence.cargopersistence.CargoEntity;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.infrastructure.persistence.cargopersistence.CargoJpaRepository;
import org.mockito.MockedStatic;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CargoServiceTest {

    @Mock
    private CargoJpaRepository cargoRepository;

    @InjectMocks
    private CargoService cargoService;

    @Test
    void deveCadastrarCargoComSucesso() {
        // Arrange
        CargoCadastroDto cargoCadastroDto = new CargoCadastroDto();
        cargoCadastroDto.setNome("Esteticista");

        CargoEntity cargoParaSalvar = new CargoEntity();
        cargoParaSalvar.setNome("Esteticista");

        CargoEntity cargoSalvo = new CargoEntity();
        cargoSalvo.setIdCargo(1);
        cargoSalvo.setNome("Esteticista");

        CargoListagemDto cargoListagemDto = new CargoListagemDto();
        cargoListagemDto.setIdCargo(1);
        cargoListagemDto.setNome("Esteticista");

        // Mocks do repositório
        when(cargoRepository.save(any(CargoEntity.class))).thenReturn(cargoSalvo);

        // Mock do mapper
        try (MockedStatic<CargoMapper> mapperMock = mockStatic(CargoMapper.class)) {
            mapperMock.when(() -> CargoMapper.toEntity(cargoCadastroDto)).thenReturn(cargoParaSalvar);
            mapperMock.when(() -> CargoMapper.toDto(cargoSalvo)).thenReturn(cargoListagemDto);

            // Act
            CargoListagemDto resultado = cargoService.cadastrarCargo(cargoCadastroDto);

            // Assert
            assertNotNull(resultado);
            assertEquals(1, resultado.getIdCargo());
            assertEquals("Esteticista", resultado.getNome());

            // Verify
            verify(cargoRepository).save(any(CargoEntity.class));
            mapperMock.verify(() -> CargoMapper.toEntity(cargoCadastroDto));
            mapperMock.verify(() -> CargoMapper.toDto(cargoSalvo));
        }
    }

    @Test
    void deveCadastrarCargoComNomeMinimo() {
        // Arrange - teste com dados mínimos
        CargoCadastroDto cargoCadastroDto = new CargoCadastroDto();
        cargoCadastroDto.setNome("Recepcionista");

        CargoEntity cargoParaSalvar = new CargoEntity();
        cargoParaSalvar.setNome("Recepcionista");

        CargoEntity cargoSalvo = new CargoEntity();
        cargoSalvo.setIdCargo(2);
        cargoSalvo.setNome("Recepcionista");

        CargoListagemDto cargoListagemDto = new CargoListagemDto();
        cargoListagemDto.setIdCargo(2);
        cargoListagemDto.setNome("Recepcionista");

        // Mocks
        when(cargoRepository.save(any(CargoEntity.class))).thenReturn(cargoSalvo);

        try (MockedStatic<CargoMapper> mapperMock = mockStatic(CargoMapper.class)) {
            mapperMock.when(() -> CargoMapper.toEntity(cargoCadastroDto)).thenReturn(cargoParaSalvar);
            mapperMock.when(() -> CargoMapper.toDto(cargoSalvo)).thenReturn(cargoListagemDto);

            // Act
            CargoListagemDto resultado = cargoService.cadastrarCargo(cargoCadastroDto);

            // Assert
            assertNotNull(resultado);
            assertEquals(2, resultado.getIdCargo());
            assertEquals("Recepcionista", resultado.getNome());

            // Verify
            verify(cargoRepository).save(any(CargoEntity.class));
            mapperMock.verify(() -> CargoMapper.toEntity(cargoCadastroDto));
            mapperMock.verify(() -> CargoMapper.toDto(cargoSalvo));
        }
    }

    @Test
    void deveListarTodosCargosComSucesso() {
        // Arrange
        CargoEntity cargo1 = new CargoEntity();
        cargo1.setIdCargo(1);
        cargo1.setNome("Esteticista");

        CargoEntity cargo2 = new CargoEntity();
        cargo2.setIdCargo(2);
        cargo2.setNome("Massoterapeuta");

        CargoEntity cargo3 = new CargoEntity();
        cargo3.setIdCargo(3);
        cargo3.setNome("Recepcionista");

        List<CargoEntity> cargos = Arrays.asList(cargo1, cargo2, cargo3);

        CargoListagemDto dto1 = new CargoListagemDto();
        dto1.setIdCargo(1);
        dto1.setNome("Esteticista");

        CargoListagemDto dto2 = new CargoListagemDto();
        dto2.setIdCargo(2);
        dto2.setNome("Massoterapeuta");

        CargoListagemDto dto3 = new CargoListagemDto();
        dto3.setIdCargo(3);
        dto3.setNome("Recepcionista");

        // Mock do repositório
        when(cargoRepository.findAll()).thenReturn(cargos);

        // Mock do mapper
        try (MockedStatic<CargoMapper> mapperMock = mockStatic(CargoMapper.class)) {
            mapperMock.when(() -> CargoMapper.toDto(cargo1)).thenReturn(dto1);
            mapperMock.when(() -> CargoMapper.toDto(cargo2)).thenReturn(dto2);
            mapperMock.when(() -> CargoMapper.toDto(cargo3)).thenReturn(dto3);

            // Act
            List<CargoListagemDto> resultado = cargoService.listarTodosCargos();

            // Assert
            assertNotNull(resultado);
            assertEquals(3, resultado.size());

            // Verificar primeiro cargo
            assertEquals(1, resultado.get(0).getIdCargo());
            assertEquals("Esteticista", resultado.get(0).getNome());

            // Verificar segundo cargo
            assertEquals(2, resultado.get(1).getIdCargo());
            assertEquals("Massoterapeuta", resultado.get(1).getNome());

            // Verificar terceiro cargo
            assertEquals(3, resultado.get(2).getIdCargo());
            assertEquals("Recepcionista", resultado.get(2).getNome());
     // Verify
            verify(cargoRepository).findAll();
            mapperMock.verify(() -> CargoMapper.toDto(cargo1));
            mapperMock.verify(() -> CargoMapper.toDto(cargo2));
            mapperMock.verify(() -> CargoMapper.toDto(cargo3));
        }
    }

    @Test
    void deveListarUmCargoComSucesso() {
        // Arrange - teste com apenas um cargo
        CargoEntity cargo = new CargoEntity();
        cargo.setIdCargo(1);
        cargo.setNome("Gerente");

        List<CargoEntity> cargos = Arrays.asList(cargo);

        CargoListagemDto cargoDto = new CargoListagemDto();
        cargoDto.setIdCargo(1);
        cargoDto.setNome("Gerente");

        // Mock do repositório
        when(cargoRepository.findAll()).thenReturn(cargos);

        // Mock do mapper
        try (MockedStatic<CargoMapper> mapperMock = mockStatic(CargoMapper.class)) {
            mapperMock.when(() -> CargoMapper.toDto(cargo)).thenReturn(cargoDto);

            // Act
            List<CargoListagemDto> resultado = cargoService.listarTodosCargos();

            // Assert
            assertNotNull(resultado);
            assertEquals(1, resultado.size());
            assertEquals(1, resultado.get(0).getIdCargo());
            assertEquals("Gerente", resultado.get(0).getNome());

            // Verify
            verify(cargoRepository).findAll();
            mapperMock.verify(() -> CargoMapper.toDto(cargo));
        }
    }

    @Test
    void deveLancarExcecaoQuandoNaoHouverCargos() {
        // Arrange
        List<CargoEntity> cargosVazio = Collections.emptyList();
        when(cargoRepository.findAll()).thenReturn(cargosVazio);

        // Act & Assert
        EntidadeNaoEncontradaException exception = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> cargoService.listarTodosCargos()
        );

        assertEquals("Nenhum Cargo Encontrado", exception.getMessage());

        // Verify
        verify(cargoRepository).findAll();

        // Verificar que o mapper nunca foi chamado
        try (MockedStatic<CargoMapper> mapperMock = mockStatic(CargoMapper.class)) {
            mapperMock.verifyNoInteractions();
        }
    }

    @Test
    void deveLancarExcecaoQuandoListaForNull() {
        // Arrange - cenário onde findAll retorna null (caso extremo)
        when(cargoRepository.findAll()).thenReturn(null);

        // Act & Assert
        assertThrows(
                NullPointerException.class,
                () -> cargoService.listarTodosCargos()
        );

        // Verify
        verify(cargoRepository).findAll();
    }

    @Test
    void deveProcessarCargosComDescricaoNula() {
        // Arrange - teste com cargo sem descrição
        CargoEntity cargo = new CargoEntity();
        cargo.setIdCargo(1);
        cargo.setNome("Auxiliar");

        List<CargoEntity> cargos = Arrays.asList(cargo);

        CargoListagemDto cargoDto = new CargoListagemDto();
        cargoDto.setIdCargo(1);
        cargoDto.setNome("Auxiliar");

        // Mock do repositório
        when(cargoRepository.findAll()).thenReturn(cargos);

        // Mock do mapper
        try (MockedStatic<CargoMapper> mapperMock = mockStatic(CargoMapper.class)) {
            mapperMock.when(() -> CargoMapper.toDto(cargo)).thenReturn(cargoDto);

            // Act
            List<CargoListagemDto> resultado = cargoService.listarTodosCargos();

            // Assert
            assertNotNull(resultado);
            assertEquals(1, resultado.size());
            assertEquals("Auxiliar", resultado.get(0).getNome());

            // Verify
            verify(cargoRepository).findAll();
            mapperMock.verify(() -> CargoMapper.toDto(cargo));
        }
    }

    @Test
    void deveBuscarCargoPorIdComSucesso() {
        // Arrange
        Integer id = 1;
        CargoEntity cargo = new CargoEntity();
        cargo.setIdCargo(id);
        cargo.setNome("Esteticista");

        CargoListagemDto cargoListagemDto = new CargoListagemDto();
        cargoListagemDto.setIdCargo(id);
        cargoListagemDto.setNome("Esteticista");

        when(cargoRepository.findById(id)).thenReturn(java.util.Optional.of(cargo));

        try (MockedStatic<CargoMapper> mapperMock = mockStatic(CargoMapper.class)) {
            mapperMock.when(() -> CargoMapper.toDto(cargo)).thenReturn(cargoListagemDto);

            // Act
            CargoListagemDto resultado = cargoService.buscarCargoPorId(id);

            // Assert
            assertNotNull(resultado);
            assertEquals(id, resultado.getIdCargo());
            assertEquals("Esteticista", resultado.getNome());

            // Verify
            verify(cargoRepository).findById(id);
            mapperMock.verify(() -> CargoMapper.toDto(cargo));
        }
    }

    @Test
    void deveLancarExcecaoQuandoCargoPorIdNaoForEncontrado() {
        // Arrange
        Integer id = 999;
        when(cargoRepository.findById(id)).thenReturn(java.util.Optional.empty());

        // Act & Assert
        EntidadeNaoEncontradaException exception = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> cargoService.buscarCargoPorId(id)
        );

        assertEquals("Cargo não Encontrado", exception.getMessage());
        verify(cargoRepository).findById(id);
    }

    @Test
    void deveAtualizarCargoComSucesso() {
        // Arrange
        Integer id = 1;
        CargoAtualizacaoDto cargoAtualizacaoDto = new CargoAtualizacaoDto();
        cargoAtualizacaoDto.setNome("Manicure");

        CargoEntity cargoExistente = new CargoEntity();
        cargoExistente.setIdCargo(id);
        cargoExistente.setNome("Esteticista");

        CargoEntity cargoAtualizado = new CargoEntity();
        cargoAtualizado.setIdCargo(id);
        cargoAtualizado.setNome("Manicure");

        CargoListagemDto cargoListagemDto = new CargoListagemDto();
        cargoListagemDto.setIdCargo(id);
        cargoListagemDto.setNome("Manicure");

        when(cargoRepository.findById(id)).thenReturn(java.util.Optional.of(cargoExistente));
        when(cargoRepository.save(any(CargoEntity.class))).thenReturn(cargoAtualizado);

        try (MockedStatic<CargoMapper> mapperMock = mockStatic(CargoMapper.class)) {
            mapperMock.when(() -> CargoMapper.toDto(cargoAtualizado)).thenReturn(cargoListagemDto);

            // Act
            CargoListagemDto resultado = cargoService.atualizarCargo(id, cargoAtualizacaoDto);

            // Assert
            assertNotNull(resultado);
            assertEquals(id, resultado.getIdCargo());
            assertEquals("Manicure", resultado.getNome());

            // Verify
            verify(cargoRepository).findById(id);
            verify(cargoRepository).save(cargoExistente);
            mapperMock.verify(() -> CargoMapper.atualizarEntity(cargoExistente, cargoAtualizacaoDto));
            mapperMock.verify(() -> CargoMapper.toDto(cargoAtualizado));
        }
    }

    @Test
    void deveLancarExcecaoQuandoCargoParaAtualizarNaoForEncontrado() {
        // Arrange
        Integer id = 999;
        CargoAtualizacaoDto cargoAtualizacaoDto = new CargoAtualizacaoDto();
        cargoAtualizacaoDto.setNome("Manicure");

        when(cargoRepository.findById(id)).thenReturn(java.util.Optional.empty());

        // Act & Assert
        EntidadeNaoEncontradaException exception = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> cargoService.atualizarCargo(id, cargoAtualizacaoDto)
        );

        assertEquals("Cargo não encontrado", exception.getMessage());
        verify(cargoRepository).findById(id);
        verify(cargoRepository, never()).save(any(CargoEntity.class));
    }

    @Test
    void deveDeletarCargoComSucesso() {
        // Arrange
        Integer id = 1;
        when(cargoRepository.existsById(id)).thenReturn(true);

        // Act
        cargoService.deletarCargo(id);

        // Assert & Verify
        verify(cargoRepository).existsById(id);
        verify(cargoRepository).deleteById(id);
    }

    @Test
    void deveLancarExcecaoQuandoCargoParaDeletarNaoForEncontrado() {
        // Arrange
        Integer id = 999;
        when(cargoRepository.existsById(id)).thenReturn(false);

        // Act & Assert
        EntidadeNaoEncontradaException exception = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> cargoService.deletarCargo(id)
        );

        assertEquals("Cargo não Encontrado", exception.getMessage());
        verify(cargoRepository).existsById(id);
        verify(cargoRepository, never()).deleteById(id);
    }
}