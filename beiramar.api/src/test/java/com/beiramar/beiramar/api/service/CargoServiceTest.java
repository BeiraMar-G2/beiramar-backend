package com.beiramar.beiramar.api.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.beiramar.beiramar.api.dto.cargoDtos.CargoAtualizacaoDto;
import com.beiramar.beiramar.api.dto.cargoDtos.CargoCadastroDto;
import com.beiramar.beiramar.api.dto.cargoDtos.CargoListagemDto;
import com.beiramar.beiramar.api.dto.mapper.CargoMapper;
import com.beiramar.beiramar.api.entity.Cargo;
import com.beiramar.beiramar.api.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.repository.CargoRepository;
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
    private CargoRepository cargoRepository;

    @InjectMocks
    private CargoService cargoService;

    @Test
    void deveCadastrarCargoComSucesso() {
        // Arrange
        CargoCadastroDto cargoCadastroDto = new CargoCadastroDto();
        cargoCadastroDto.setNome("Esteticista");

        Cargo cargoParaSalvar = new Cargo();
        cargoParaSalvar.setNome("Esteticista");

        Cargo cargoSalvo = new Cargo();
        cargoSalvo.setIdCargo(1);
        cargoSalvo.setNome("Esteticista");

        CargoListagemDto cargoListagemDto = new CargoListagemDto();
        cargoListagemDto.setIdCargo(1);
        cargoListagemDto.setNome("Esteticista");

        // Mocks do repositório
        when(cargoRepository.save(any(Cargo.class))).thenReturn(cargoSalvo);

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
            verify(cargoRepository).save(any(Cargo.class));
            mapperMock.verify(() -> CargoMapper.toEntity(cargoCadastroDto));
            mapperMock.verify(() -> CargoMapper.toDto(cargoSalvo));
        }
    }

    @Test
    void deveCadastrarCargoComNomeMinimo() {
        // Arrange - teste com dados mínimos
        CargoCadastroDto cargoCadastroDto = new CargoCadastroDto();
        cargoCadastroDto.setNome("Recepcionista");

        Cargo cargoParaSalvar = new Cargo();
        cargoParaSalvar.setNome("Recepcionista");

        Cargo cargoSalvo = new Cargo();
        cargoSalvo.setIdCargo(2);
        cargoSalvo.setNome("Recepcionista");

        CargoListagemDto cargoListagemDto = new CargoListagemDto();
        cargoListagemDto.setIdCargo(2);
        cargoListagemDto.setNome("Recepcionista");

        // Mocks
        when(cargoRepository.save(any(Cargo.class))).thenReturn(cargoSalvo);

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
            verify(cargoRepository).save(any(Cargo.class));
            mapperMock.verify(() -> CargoMapper.toEntity(cargoCadastroDto));
            mapperMock.verify(() -> CargoMapper.toDto(cargoSalvo));
        }
    }

    @Test
    void deveListarTodosCargosComSucesso() {
        // Arrange
        Cargo cargo1 = new Cargo();
        cargo1.setIdCargo(1);
        cargo1.setNome("Esteticista");

        Cargo cargo2 = new Cargo();
        cargo2.setIdCargo(2);
        cargo2.setNome("Massoterapeuta");

        Cargo cargo3 = new Cargo();
        cargo3.setIdCargo(3);
        cargo3.setNome("Recepcionista");

        List<Cargo> cargos = Arrays.asList(cargo1, cargo2, cargo3);

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
        Cargo cargo = new Cargo();
        cargo.setIdCargo(1);
        cargo.setNome("Gerente");

        List<Cargo> cargos = Arrays.asList(cargo);

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
        List<Cargo> cargosVazio = Collections.emptyList();
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
        Cargo cargo = new Cargo();
        cargo.setIdCargo(1);
        cargo.setNome("Auxiliar");

        List<Cargo> cargos = Arrays.asList(cargo);

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
        Cargo cargo = new Cargo();
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

        Cargo cargoExistente = new Cargo();
        cargoExistente.setIdCargo(id);
        cargoExistente.setNome("Esteticista");

        Cargo cargoAtualizado = new Cargo();
        cargoAtualizado.setIdCargo(id);
        cargoAtualizado.setNome("Manicure");

        CargoListagemDto cargoListagemDto = new CargoListagemDto();
        cargoListagemDto.setIdCargo(id);
        cargoListagemDto.setNome("Manicure");

        when(cargoRepository.findById(id)).thenReturn(java.util.Optional.of(cargoExistente));
        when(cargoRepository.save(any(Cargo.class))).thenReturn(cargoAtualizado);

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
        verify(cargoRepository, never()).save(any(Cargo.class));
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