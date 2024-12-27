package tec.edu.azuay.sale.service.implement;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tec.edu.azuay.sale.dto.requests.BillRequestDto;
import tec.edu.azuay.sale.dto.responses.BillDto;
import tec.edu.azuay.sale.dto.responses.BillItemsDto;
import tec.edu.azuay.sale.dto.responses.BillWithDetailsDto;
import tec.edu.azuay.sale.exceptions.ObjectNotFoundException;
import tec.edu.azuay.sale.persistence.models.Bill;
import tec.edu.azuay.sale.persistence.repository.IBillRepository;
import tec.edu.azuay.sale.persistence.repository.IPayMethodRepository;
import tec.edu.azuay.sale.persistence.repository.IPersonRepository;
import tec.edu.azuay.sale.service.interfaces.IGenericService;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements IGenericService<BillDto, BillRequestDto> {

    private final IBillRepository billRepository;

    private final IPersonRepository personRepository;

    private final IPayMethodRepository payMethodRepository;

    private final ModelMapper modelMapper;

    private BillDto entityToDto(Bill entity) {
        return modelMapper.map(entity, BillDto.class);
    }

    private Bill dtoToEntity(BillRequestDto request) {
        return modelMapper.map(request, Bill.class);
    }

    @Override
    public BillDto save(BillRequestDto item) {

        payMethodRepository.findById(item.getPayMethodId()).
                orElseThrow(
                        () -> new ObjectNotFoundException("PayMethod with id %s not found".formatted(item.getPayMethodId()))
                );

        personRepository.findById(item.getPersonId()).orElseThrow(
                () -> new ObjectNotFoundException("Person with id %s not found".formatted(item.getPersonId()))
        );

        Bill bill = billRepository.save(dtoToEntity(item));

        return entityToDto(bill);
    }

    @Override
    public BillDto getById(Long itemId) {
        return entityToDto(billRepository.findById(itemId).orElseThrow(() -> new ObjectNotFoundException("Bill not found")));
    }

    @Override
    public List<BillDto> getAllItems() {
        return billRepository.findAll().stream().map(this::entityToDto).toList();
    }

    @Override
    public BillDto updateItem(BillRequestDto item, Long itemId) {
        Bill billToUpdate = billRepository.findById(itemId).orElseThrow(() -> new ObjectNotFoundException("Bill not found"));

        billToUpdate.setRuc(Objects.nonNull(item.getRuc()) ? item.getRuc() : billToUpdate.getRuc());
        billToUpdate.setDate((Objects.nonNull(item.getDate()) ? item.getDate() : billToUpdate.getDate()));
        billToUpdate.setTotal((Objects.nonNull(item.getTotal()) ? item.getTotal() : billToUpdate.getTotal()));
        billToUpdate.setDiscount((Objects.nonNull(item.getDiscount()) ? item.getDiscount() : billToUpdate.getDiscount()));

        personRepository.findById(item.getPersonId()).ifPresentOrElse(
                billToUpdate::setPerson,
                () -> {
                    throw new ObjectNotFoundException("Person with id %s not found".formatted(item.getPersonId()));
                }
        );

        payMethodRepository.findById(item.getPayMethodId()).ifPresentOrElse(
                billToUpdate::setPayMethod,
                () -> {
                    throw new ObjectNotFoundException("PayMethod with id %s not found".formatted(item.getPayMethodId()));
                }
        );

        return entityToDto(billRepository.save(billToUpdate));
    }

    @Override
    public void deleteItem(Long itemId) {
        billRepository.deleteById(itemId);
    }

    public BillWithDetailsDto getBillWithDetails(Long billId) {
        Bill bill = billRepository.findById(billId).orElseThrow(() -> new ObjectNotFoundException("Bill not found"));
        BillWithDetailsDto billWithDetailsDto = modelMapper.map(bill, BillWithDetailsDto.class);
        billWithDetailsDto.setBillItems(bill.getBillItems().stream().map(billItem -> modelMapper.map(billItem, BillItemsDto.class)).toList());

        return billWithDetailsDto;
    }
}
